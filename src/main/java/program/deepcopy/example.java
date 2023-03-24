package program.deepcopy;

public class example {
    public static class User {
        private final String name;
        private final Car car;

        public User(String name, Car car) {
            this.name = name;
            this.car = car;
        }

        public String getName() {
            return name;
        }

        public Car getCar() {
            return car;
        }
    }

    public static class Car {
        private String name;

        public Car(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static void main(String[] args) {
        User user = new User("택상", new Car("벤츠"));
        /*User deepCopiedUser = new User(user.getName(), user.getCar());*/
        User deepCopiedUser = new User(user.getName() , new Car(user.getCar().getName()));

        user.getCar().setName("산타페");


        System.out.println(user.getCar().getName());
        System.out.println(deepCopiedUser.getCar().getName());
    }
}
