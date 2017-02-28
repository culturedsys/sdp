# When is it appropriate to use the Abstract Factory design pattern?

The abstract factory pattern is appropriate when you have a family of related types, and you want to be able to
switch between entire families at once. That is, you have interfaces A and B, and implementations SomeAImpl,
SomeBImpl, OtherAImpl, OtherBImpl, and you always want to use SomeAImpl with SomeBImpl, or OtherAImpl with OtherBImpl
(but you don't want to mix-and-match, and use SomeAImpl with OtherBImpl). The abstract factory pattern allows for
switching between these implementation families at run time.