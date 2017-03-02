# When should one make use of the Bridge Design Pattern?

The bridge pattern is used when you have a hierarchy of classes which can be implemented in terms of some set of
operations, where that set of operations itself can have multiple different implementations.

I've implemented a different example of the bridge pattern here from the question in the worksheet, which I think more
clearly illustrates the pattern. In my example here, there is a `Lock` class, representing a car lock that can be
activated in some way to lock or unlock the car, using the `activate` method. There are two subclasses, `KeyLock` and
`RadioLock`, representing a lock using a physical key and a lock using a radio system, respectively. The locking and
unlocking is implemented by a another class that provides the `isLocked`, `lock`, and `unlock` methods (specified in the
`LockingSystem` trait).