create user fevzi with password '';
CREATE DATABASE foo  OWNER fevzi;

CREATE TABLE foo (
  a   int,
  b  int,
  c int
);

CREATE DATABASE bar OWNER fevzi;
CREATE TABLE bar (
  a int,
  b int,
  c int
);
