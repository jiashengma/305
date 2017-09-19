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
    aid             CHAR(2), -- airline id
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
    departureTime   DATETIME-- TODO: type?
    arrivalTime     -- TODO: type?

    PRIMARY KEY (id),
    FOREIGN KEY (aid) REFERENCES airline(id),
    FOREIGN KEY (flightNumber) REFERENCES flight
    FOREIGN KEY (fromAirport) REFERENCES airport(id),
    FOREIGN KEY (toAirport) REFERENCES airport(id),

);

-- flight operates on what day(s)
CREATE TABLE operatesOn (
    flightNumber    INTEGER,
    aid             INTEGER,
    day             INTEGER(1),
    PRIMARY KEY (flightNumber, aid, day)
);

CREATE TABLE reservation (
    reservationNumber      INTEGER, 
    -- TODO: set default to current date on create 
    reservationDate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    totalFare   FLOAT(10,2),
    bookingFee  FLOAT(10,2),
    eid         INTEGER, -- employee (customer representative) id
    
    PRIMARY KEY (reservationNumber),
    -- TODO: create an id for customer representative or use ssn?
    FOREIGN KEY (eid) REFERENCES employee(ssn)
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

    FOREIGN KEY (reservationNumber) REFERENCES reservation,
    FOREIGN KEY (fromAirport) REFERENCES airport(id),
    FOREIGN KEY (toAirport) REFERENCES airport(id),
    FOREIGN KEY (aid) REFERENCES airline(id)
);

-- TODO: fare restrictions

CREATE TABLE person (
    id          INTEGER,
    lastname    VARCHAR(32) NOT NULL,
    firstname   VARCHAR(32) NOT NULL,
    address     VARCHAR(64) NOT NULL,
    city        VARCHAR(32) NOT NULL,
    state       CHAR(2) NOT NULL,
    zipcode     INTEGER(5) NOT NULL,
    phone       CHAR() NOT NULL, -- TODO: CHECK WHAT FORMAT
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
    FOREIGN KEY (id) REFERENCES person

);

-- who reserves what 
CREATE TABLE reserves (
    customer            INTEGER, -- customer account number
    reservationNumber   INTEGER,

    PRIMARY KEY (cid, reservationNumber)
);

-- customer's preferene 
CREATE TABLE preference (
    customer    INTEGER, -- customer account number
    seat        ENUM('Aisle Seat', 'Window Seat'),
    meal        ENUM('Meal 1', 'Meal 2'), -- TODO: check meal options \
    FOREIGN KEY (customer)
);

CREATE TABLE employee(
    id          INTEGER UNIQUE NOT NULL,
    ssn         CHAR(12),
    isManager   TINYINT DEFUALT 0 NOT NULL, -- customer representative
    startDate   DATE , -- TODO: DEFAULT DATE ON CREATE?
    hourlyRate  FLOAT(5,2),
    PRIMARY KEY(ssn),
    FOREIGN KEY (id) REFERENCES person
);
