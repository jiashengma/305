-- Author: Jia Sheng Ma

-- TODO: keys, constraints
CREATE TABLE airline (
    id      INTEGER,
    name    CHAR(2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE airport (
    id      INTEGER,
    name    CHAR(3)  NOT NULL,
    city    VARCHAR(64) NOT NULL,
    country VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

-- TODO
CREATE TABLE flight (
    flightNumber    INTEGER,
    aid             INTEGER, -- airline id
    numSeat         INTEGER,
    -- ENUM('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun')
    fare            FLOAT(10,2),
    -- lowest bid the airline is willing to accept
    hiddenFare      FLOAT(10,2) NOT NULL, 
    -- TODO: fare restrictions?
    PRIMARY KEY (flightNumber, aid),
    FOREIGN KEY (aid) REFERENCES airline(id)
);

CREATE TABLE stops (
    id              INTEGER,    -- stop number
    aid             INTEGER NOT NULL,  -- airline id
    flightNumber    INTEGER NOT NULL,
    /* TODO: can we use airports instead of citys as stops? b/c flight stops
            at airport
    */ 
    fromAirport     INTEGER NOT NULL,
    toAirport       INTEGER NOT NULL,
    departureTime   DATETIME,-- TODO: type?
    arrivalTime     DATETIME,-- TODO: type?

    PRIMARY KEY (id),
    FOREIGN KEY (flightNumber,aid) REFERENCES flight(flightNumber,aid),
    FOREIGN KEY (fromAirport) REFERENCES airport(id),
    FOREIGN KEY (toAirport) REFERENCES airport(id)

);

-- flight operates on what day(s)
CREATE TABLE operatesOn (
    flightNumber    INTEGER,
    aid             INTEGER,
    day             INTEGER(1),
    PRIMARY KEY (flightNumber, aid, day)
);

CREATE TABLE person (
    id          INTEGER,
    lastname    VARCHAR(32) NOT NULL,
    firstname   VARCHAR(32) NOT NULL,
    address     VARCHAR(64) NOT NULL,
    city        VARCHAR(32) NOT NULL,
    state       CHAR(2) NOT NULL,
    zipcode     INTEGER(5) NOT NULL,
    phone       CHAR(10) NOT NULL, -- TODO: CHECK WHAT FORMAT
    email       VARCHAR(64) NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE customer (
    id          INTEGER UNIQUE NOT NULL,
    account     INTEGER NOT NULL,    -- account number
    accountCreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    creditCardNum   BIGINT NOT NULL, 
    rating      INTEGER DEFAULT 0, -- each ticket transcation rating should +1

    PRIMARY KEY(account),
    FOREIGN KEY (id) REFERENCES person(id)

);


CREATE TABLE employee(
    id          INTEGER UNIQUE NOT NULL,
    ssn         CHAR(12),
    isManager   TINYINT DEFAULT 0 NOT NULL, -- customer representative
    startDate   DATE , -- TODO: DEFAULT DATE ON CREATE?
    hourlyRate  FLOAT(5,2),
    PRIMARY KEY(ssn),
    FOREIGN KEY (id) REFERENCES person(id)
);
-- customer's preferene 
CREATE TABLE preference (
    customer    INTEGER, -- customer account number
    seat        ENUM('Aisle Seat', 'Window Seat'),
    meal        ENUM('Meal 1', 'Meal 2'), -- TODO: check meal options \
    FOREIGN KEY (customer) REFERENCES customer(account)
);
CREATE TABLE reservation (
    reservationNumber      INTEGER, 
    -- TODO: set default to current date on create 
    reservationDate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    totalFare   FLOAT(10,2),
    bookingFee  FLOAT(10,2),
    seatCount   INTEGER NOT NULL DEFAULT 1,
    customer    INTEGER NOT NULL,
    eid         INTEGER, -- employee (customer representative) id
    
    PRIMARY KEY (reservationNumber),
    -- TODO: create an id for customer representative or use ssn?
    -- FOREIGN KEY (eid) REFERENCES employee(ssn),
    -- ^ eid & ssn types don't match.
    FOREIGN KEY (customer) REFERENCES customer (account)
);

CREATE TABLE leg (
    reservationNumber   INTEGER,
    fromAirport     INTEGER,
    toAirport       INTEGER,
    flightNumber    INTEGER,
    aid             INTEGER,
    seatNumber      INTEGER,
    departureTime   TIMESTAMP,
    arrivalTime     TIMESTAMP,
    meal            VARCHAR(32), -- TODO: USE ENUM?
    class          ENUM('Economy','Business','First'),

    FOREIGN KEY (reservationNumber) REFERENCES reservation(reservationNumber),
    FOREIGN KEY (fromAirport) REFERENCES airport(id),
    FOREIGN KEY (toAirport) REFERENCES airport(id),
    FOREIGN KEY (aid) REFERENCES airline(id)
);

-- TODO: fare restrictions

/* At the moment, this will count the number of seats found in the
Leg table. This will have to change if we update the schema to match
the E-R diagram. */
CREATE VIEW FlightCount AS
SELECT aid, flightNumber, COUNT(seatNumber) FROM leg
GROUP BY aid, flightNumber;

