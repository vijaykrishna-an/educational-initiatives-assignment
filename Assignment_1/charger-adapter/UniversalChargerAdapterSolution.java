package Day1.Assign1.Structural.Adapter;

interface ChargingAdapter {
    public void chargeDevice();
}

class USB_A {
    public void chargeA() {
        System.out.println("Charging with A");
    }
}

class USB_B {
    public void chargeB() {
        System.out.println("Charging with B");
    }
}

class USB_C {
    public void chargeC() {
        System.out.println("Charging with C");
    }
}

class Lightening_C {
    public void chargeLightening() {
        System.out.println("Charging with Ligntening");
    }
}

class UniversalAdapter implements ChargingAdapter {
    private Object charger;
    UniversalAdapter(Object charger) {
        this.charger = charger;
    }

    public void chargeDevice() {
        if (charger instanceof USB_A) {
            ((USB_A) charger).chargeA();
        } else if (charger instanceof USB_B) {
            ((USB_B) charger).chargeB();
        } else if (charger instanceof USB_C) {
            ((USB_C) charger).chargeC();
        } else if (charger instanceof Lightening_C) {
            ((Lightening_C) charger).chargeLightening();
        } else {
            System.out.println("Chrging with Anonymous Port");
        }
    }
}

class UniversalChargerAdapterSolution {
    public static void main(String args[]) {
        UniversalAdapter usbAdapter = new UniversalAdapter(new USB_A());
        usbAdapter.chargeDevice();
        usbAdapter = new UniversalAdapter(new USB_B());
        usbAdapter.chargeDevice();
        usbAdapter = new UniversalAdapter(new USB_C());
        usbAdapter.chargeDevice();
        usbAdapter = new UniversalAdapter(new Lightening_C());
        usbAdapter.chargeDevice();
    }
}