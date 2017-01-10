public class Driver {
    public static void main(String[] args) {
        // Using generics here allows the code for Storage to be shared between the classes for storing BankAccount
        // and String, while also allowing the compiler to treat the two differently, i.e., reporting an error if you
        // try and save a BankAccount object in a Storage intended to work with Strings.
        Storage<BankAccount> aStorage = new Storage<>();
        Storage<String> sStorage = new Storage<>();

        // Class baCls = BankAccount.class;                                // (2)
        // Replacing the line above with the line below, the program compiles without error.
        Class<BankAccount> baCls = BankAccount.class;
        try {
            // Object myAccount = baCls.newInstance();                  // (1)

            // With line (2) above, the line below will not compile, because baCls.newInstance() is declared as
            // returning an Object, not a BankAccount. The compiler does not know that the object being returned is in
            // fact a BankAccount instance.
            // BankAccount myAccount = baCls.newInstance();

            // The dynamic cast below checks (when the program is run by the JVM) that the object returned by
            // baCls.newInstance() is a BankAccount (or a subclass of BankAccount) instance. As the code is currently
            // written, we know that this will always be the case (because baCls is equal to BankAccount.class, i.e.,
            // the object representing the BankAccount class, and not any other class), but if the code that initialises
            // baCls were changed (perhaps to represent different classes depending on user options),
            // baCls.newInstance() might return something that was not an instance of BankAccount or a subclass; in
            // this case, the cast would throw an exception. This is "safe" in the sense that it is predictable, and
            // wouldn't e.g. corrupt data elsewhere in the program, but (as we are not handling the exception
            // anywhere in the program) would cause the program to crash.
            BankAccount myAccount = (BankAccount) baCls.newInstance();

            // Using line (1) above, line below will not compile, because myAccount is an Object, but setValue
            // expects a BankAccount
            aStorage.setValue(myAccount);
            // Using line (1) above, line below will not compile, because myAccount is an Object, which has no deposit
            // method.
            myAccount.deposit(15);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Outputs 115.0
        System.out.println(aStorage.getValue().showBalance());

        // Outputs EQUAL.
        // This is because Java generics only exist at compile time. At run time, the generic class is implemented by
        // a single class that operates on Object instances. The type parameters to the generic class are only used
        // for compile-time checks. This means that the information about them is not available through run-time
        // reflection.
        if (aStorage.getClass() == sStorage.getClass()) {
            System.out.println("EQUAL");
        } else {
            System.out.println("NOT EQUAL");
        }
    }
}
