# Compiler
JAVAC = javac
JAVA = java

# Source files
SOURCES = $(wildcard *.java)

# Class files
CLASSES = $(SOURCES:.java=.class)

# Main class
MAIN_CLASS = Main

# Default target
.PHONY: all
all: run

# Compile all Java files
compile: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

# Run the main program
run: compile
	$(JAVA) $(MAIN_CLASS)

# Clean up class files
.PHONY: clean
clean:
	rm -f *.class

