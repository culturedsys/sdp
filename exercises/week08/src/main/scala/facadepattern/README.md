# What is the Facade Pattern?

The facade pattern is used when you provide a simpler interface to a complex subsystem.

# When, and why, would you use the Facade Pattern?

The facade pattern is used when you have a subsystem with a complex interface (either as a result of previous
development, or because some other parts of the program need that complex level of flexibility), but you also have
some clients of that subsystem which only have comparatively simple needs. Using the facade pattern can simplify the
code in those clients, without requiring that the subsystem itself be changed.