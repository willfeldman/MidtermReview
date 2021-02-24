/*
    Practice question:
    Design an interface IComputer and create classes Macs and PC. Macs have a name (String), a model number (String),
    and a price. Additionally, Macs have a OSX version name (String). PCs have a windows version (int).

    In IComputer, define and implement a method that produces a String containing all the information about that
    computer.

    Make a method sameComputer that checks if two computers are the same.

    Make a method onSale(int discount) that takes in a discount percent and returns that computer with the new price.
 */

import tester.*;

interface IComputer {
    String information(); // return information about a computer
    boolean sameComputer(IComputer that); // is this computer the same as that computer?
    boolean sameMac(Mac that); // is this computer the same as that Mac?
    boolean samePC(PC that); // is this computer the same as that PC?
    IComputer onSale(int discount); // return new computer with given sales discount
}

class Mac implements IComputer {
    String name;
    String modelNumber;
    int price;
    String osxVersion;

    // the constructor
    Mac(String name, String modelNumber, int price, String osxVersion) {
        this.name = name;
        this.modelNumber = modelNumber;
        this.price = price;
        this.osxVersion = osxVersion;
    }

    // return information about this Mac
    public String information() {
        return "This " + this.name + " is model " +
                this.modelNumber + " running " +
                this.osxVersion + " and costs " +
                this.price;
    }

    // is this Mac the same as that computer?
    public boolean sameComputer(IComputer that) {
        return that.sameMac(this);
    }

    // is this Mac the same as that Mac?
    public boolean sameMac(Mac that) {
        return (this.name.equals(that.name))
                && (this.modelNumber.equals(that.modelNumber))
                && (this.price == that.price)
                && (this.osxVersion.equals(that.osxVersion));
    }

    // is this Mac the same as that PC?
    public boolean samePC(PC that) {
        return false;
    }

    // return new Mac with given sales discount
    public IComputer onSale(int discount) {
        return new Mac(this.name, this.modelNumber, (this.price - (this.price * discount / 100)), this.osxVersion);
    }
}

class PC implements IComputer {
    String name;
    String modelNumber;
    int price;
    int windowsVersion;

    // the constructor
    PC(String name, String modelNumber, int price, int windowsVersion) {
        this.name = name;
        this.modelNumber = modelNumber;
        this.price = price;
        this.windowsVersion = windowsVersion;
    }

    // return information about this PC
    public String information() {
        return "This " + this.name + " is model " +
                this.modelNumber + " running Windows " +
                this.windowsVersion + " and costs " +
                this.price;
    }

    // is this PC the same as that computer?
    public boolean sameComputer(IComputer that) {
        return that.samePC(this);
    }

    // is this PC the same as that Mac?
    public boolean sameMac(Mac that) {
        return false;
    }

    // is this PC the same as that PC?
    public boolean samePC(PC that) {
        return (this.name.equals(that.name))
                && (this.modelNumber.equals(that.modelNumber))
                && (this.price == that.price)
                && (this.windowsVersion == that.windowsVersion);
    }

    // return new PC with given sales discount
    public IComputer onSale(int discount) {
        return new PC(this.name, this.modelNumber, (this.price - (this.price * discount / 100)), this.windowsVersion);
    }
}

class ExamplesComputer {
    IComputer macbookAir = new Mac("MacBook Air", "A12BA1", 899, "Big Sur");
    IComputer macbookPro = new Mac("MacBook Pro", "A12UJ5", 1299, "Catalina");
    IComputer dell = new PC("Dell Vive 3", "3HJAK", 649, 8);

    // tests for information
    boolean testInformation(Tester t) {
        return t.checkExpect(this.macbookAir.information(), "This MacBook Air is model A12BA1 running Big Sur and costs 899")
                && t.checkExpect(this.macbookPro.information(), "This MacBook Pro is model A12UJ5 running Catalina and costs 1299")
                && t.checkExpect(this.dell.information(), "This Dell Vive 3 is model 3HJAK running Windows 8 and costs 649");
    }

    // tests for sameComputer
    boolean testSameComputer(Tester t) {
        return t.checkExpect(this.macbookAir.sameComputer(this.macbookAir), true)
                && t.checkExpect(this.macbookPro.sameComputer(this.macbookAir), false)
                && t.checkExpect(this.dell.sameComputer(this.dell), true)
                && t.checkExpect(this.macbookAir.sameComputer(this.dell), false);
    }

    // tests for onSale
    boolean testOnSale(Tester t) {
        return t.checkExpect(this.macbookAir.onSale(20), new Mac("MacBook Air", "A12BA1", 720, "Big Sur"))
                && t.checkExpect(this.dell.onSale(50), new PC("Dell Vive 3", "3HJAK", 325, 8));
    }
}