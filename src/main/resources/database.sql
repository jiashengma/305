-- Author: Jia Sheng Ma

-- TODO: keys, constraints
CREATE TABLE airline (
    id      INTEGER,
    name    CHAR(2),
    PRIMARY KEY (id)
);

CREATE TABLE airport (
    id      INTEGER,
    name    CHAR(3),
    city    VARCHAR(64),
    country VARCHAR(64),
    PRIMARY KEY (id)
);

-- TODO
CREATE TABLE flight (
    flightNumber    INTEGER,
    aid             INTEGER, -- airline id
    numSeat         INTEGER,
    -- ENUM('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun')
    fare            FLOAT(10,2),
    hiddenFare      FLOAT(10,2), -- lowest bid the airline is willing to accept
    -- TODO: fare restrictions?
    PRIMARY KEY (flightNumber),
    FOREIGN KEY (aid) REFERENCES airline(id)
);

CREATE TABLE stops (
    aid             INTEGER, -- airline id
    flightNumber    INTEGER,
    departureCity
    departureCountry
    departureTime
    arrivalCity
    arrivalCountry
    arrivalTime
    
    --TODO: primary key?
    FOREIGN KEY (aid) REFERENCES airline(id,
    FOREIGN KEY (flightNumber) REFERENCES flight
    
);

-- flight operates on what day(s)
CREATE TABLE operatesOn (
    flightNumber    INTEGER,
    day             INTEGER(1),
    PRIMARY KEY (flightNumber, day)
);

CREATE TABLE reservation (
    reservationNumber      INTEGER, 
    -- TODO: set default to current date on create 
    reservationDate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    totalFare   FLOAT(10,2),
    bookingFee  FLOAT(10,2),
    eid         INTEGER, -- employee (customer representative) id
    
    PRIMARY KEY (reservationNumber),
    -- TODO: create an id for customer representative or use ssn
    FOREIGN KEY (eid) REFERENCES employee(ssn)
);

CREATE TABLE leg (
    reservationNumber   INTEGER,
    fromAirport         INTEGER,
    toAirport           INTEGER,
    flightNumber        INTEGER,
    aid                 INTEGER
    departureTime   TIMESTAMP,
    arrivalTime     TIMESTAMP,
    class       ENUM('Economy','Business','First'),
    seatNumber  INTEGER,

    FOREIGN KEY (reservationNumber) REFERENCES reservation,
    FOREIGN KEY (fromAirport) REFERENCES airport(id),
    FOREIGN KEY (toAirport) REFERENCES airport(id),
    FOREIGN KEY (aid) REFERENCES airline(id)
    
);

CREATE TABLE customer (
    lastname    VARCHAR(32),
    firstname   VARCHAR(32),
    address     VARCHAR(64),
    city        VARCHAR(32),
    state       CHAR(2),
    zipcode     INTEGER(5),
    phone       INTEGER(9), -- TODO: CHECK
    email       VARCHAR(64),
    account     INTEGER,    -- account number
    accountCreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creditCardNum   -- TODO
    -- TODO: preferences -> from another table
    rating      --TODO: type?
    
    PRIMARY KEY(account)

);

-- who reserves 
CREATE TABLE reserves (
    cid                 INTEGER, -- customer
    reservationNumber   INTEGER,

    PRIMARY KEY (cid, reservationNumber)
);

CREATE TABLE employee(
    ssn         INTEGER,
    lastname    VARCHAR(32),
    firstname   VARCHAR(32),
    address     VARCHAR(64),
    city        VARCHAR(32),
    state       CHAR(2),
    zipcode     INTEGER(5),
    phone       INTEGER(9), -- TODO: CHECK

    startDate   DATE , -- TODO: DEFAULT DATE ON CREATE?
    hourlyRate  FLOAT(3,2),
    PRIMARY KEY(ssn)
);

