# Scala Build Tool
SBT = sbt

all: run

clean:
	@echo clean
	@$(SBT) clean

compile:
	@echo compile
	@$(SBT) compile

run:
	@echo run
	@$(SBT) run

re:	clean compile

version:
	@$(SBT) version