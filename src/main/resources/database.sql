-- Author: AJA(x) - Antonio Aliberti, Jia Sheng Ma, Andrew Lam

CREATE TABLE airline (
    id      INTEGER,                    -- Airline ID for query reference
    name    CHAR(2) NOT NULL,           -- 2Letter name for display purposes
    longName VARCHAR(60) NOT NULL,      -- Full Name of this airline
    PRIMARY KEY (id),
    UNIQUE (name, longName);
);

CREATE TABLE airport (
    id      INTEGER,                    -- Airport ID for query reference
    name    CHAR(3)  NOT NULL,          -- 3Letter name >> UNIQUE per airline ONLY >> display purpose
    longName VARCHAR(64) NOT NULL,      -- Full Name of this airport
    city    VARCHAR(64) NOT NULL,       -- City where airport is located
    country VARCHAR(64) NOT NULL,       -- Country where airport is located

    -- TODO: May need a TIMEZONE to properly display times if use epoch timings
    PRIMARY KEY (id),
    UNIQUE (name, longName);
);

CREATE TABLE flight (
    flightNumber    INTEGER,            -- Flight ID for query reference-
    aid             INTEGER,            -- Airline id
    numSeat         INTEGER,
    -- ENUM('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun')
    fare            FLOAT(10,2),		-- Standard price on this flight
    hiddenFare      FLOAT(10,2) NOT NULL,-- lowest bid the airline is willing to accept
    advancePurchase     ENUM(3, 7, 14, 21), -- pay # days in advance
    lengthOfStay        INTEGER,            -- TODO: ENUM or INT

    PRIMARY KEY (flightNumber, aid),
    FOREIGN KEY (aid) REFERENCES airline(id)
);

-- flight operates on what day(s)
CREATE TABLE operatesOn (
    flightNumber    INTEGER,
    aid             INTEGER,
    day             INTEGER(1),
    -- ENUM('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun') ?
    PRIMARY KEY (flightNumber, aid, day),
    FOREIGN KEY (aid) REFERENCES airline(id),
    FOREIGN KEY (flightNumber) REFERENCES flight
);

CREATE TABLE person (
    id          INTEGER,
    lastname    VARCHAR(32) NOT NULL,
    firstname   VARCHAR(32) NOT NULL,
    address     VARCHAR(64) NOT NULL,
    city        VARCHAR(32) NOT NULL,
    state       CHAR(2) NOT NULL,
    zipcode     INTEGER(5) NOT NULL,
    phone       CHAR(10) NOT NULL, 		-- TODO: CHECK WHAT FORMAT
    email       VARCHAR(64) NOT NULL,

    PRIMARY KEY (id)
);
CREATE TABLE customer (
    id          INTEGER UNIQUE NOT NULL,-- person ID
    account     INTEGER NOT NULL,    	-- account number
    accountCreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,	-- would this ever become null if defaults to CURRENT_TIMESTAMP
    creditCardNum   BIGINT NOT NULL,
    rating      INTEGER DEFAULT 0, 		-- each ticket transcation rating should +1

    PRIMARY KEY(account),
    FOREIGN KEY (id) REFERENCES person(id)
);


CREATE TABLE employee(
    id          INTEGER UNIQUE NOT NULL,
    ssn         CHAR(12),
    isManager   TINYINT DEFAULT 0 NOT NULL, -- customer representative
    startDate   DATE, -- TODO: DEFAULT DATE ON CREATE?	Date started working?
    hourlyRate  FLOAT(5,2),
    PRIMARY KEY(ssn),
    FOREIGN KEY (id) REFERENCES person(id)
);

CREATE TABLE reservation (
    reservationNumber      INTEGER, 
    -- TODO: set default to current date on create 
    reservationDate   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    totalFare   FLOAT(10,2),			-- Does this include the bookingFee?
    bookingFee  FLOAT(10,2),
    seatCount   INTEGER NOT NULL DEFAULT 1,
    eid         INTEGER, 				-- employee (customer representative) id
    
    PRIMARY KEY (reservationNumber),
    FOREIGN KEY (customer) REFERENCES customer (account)
	FOREIGN KEY (eid) REFERENCES employee (eid)
);

-- who reserves what
CREATE TABLE reserves (
    customer            INTEGER, -- customer account number
    reservationNumber   INTEGER,

    PRIMARY KEY (cid, reservationNumber)
);

CREATE TABLE leg (
    reservationNumber   INTEGER,
    fromAirport     INTEGER NOT NULL,
    toAirport       INTEGER NOT NULL,
    flightNumber    INTEGER NOT NULL,
    aid             INTEGER NOT NULL,
    seatNumber      INTEGER,
    departureTime   TIMESTAMP,
    arrivalTime     TIMESTAMP,
    meal            VARCHAR(32), -- TODO: USE ENUM?
    class          ENUM('Economy','Business','First'),

    FOREIGN KEY (reservationNumber) REFERENCES reservation(reservationNumber),
    FOREIGN KEY (fromAirport) REFERENCES airport(id),
    FOREIGN KEY (toAirport) REFERENCES airport(id),
    FOREIGN KEY (flightNumber, aid) REFERENCES flight(flightNumber, aid)
);


/* At the moment, this will count the number of seats found in the
Leg table. This will have to change if we update the schema to match
the E-R diagram. */
CREATE VIEW FlightCount AS
SELECT aid, flightNumber, COUNT(seatNumber) FROM leg
GROUP BY aid, flightNumber;

