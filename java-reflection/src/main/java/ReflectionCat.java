import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionCat {
    public static void main(String[] args) throws Exception {
        Cat myCat = new Cat("Stella", 6);
        Field[] catFields = myCat.getClass().getDeclaredFields();

        for(Field field : catFields) {
            if(field.getName().equals("name")){
                field.setAccessible(true);
                field.set(myCat, "Nome do gato alterado para Jimmy McGrill");
            }
            System.out.println("Campo: " + field.getName() + " => Tipo: " + field.toString());
        }
        System.out.println(myCat.getName());

        Method[] catMethods = myCat.getClass().getDeclaredMethods();
        System.out.println("Invoking all methods:");
        for (Method method : catMethods) {
            System.out.println("Metodo: " + method.getName());
            if(method.getName().equals("meow")){
                myCat.meow();
            }
            try {
                method.invoke(myCat);
            } catch(Exception e){
                System.out.println("Metodo falhou");
            }
            if(method.getName().equals("heyThisIsPrivate")){
                method.setAccessible(true);
                System.out.println("Agora funciona:");
                method.invoke(myCat);
            }
            if(method.getName().equals("thisIsAPublicStaticMethod")){
                method.invoke(null); //passing null to static methods works
            }
            if(method.getName().equals("thisIsAPrivateAndStaticMethod")){
                method.setAccessible(true);
                method.invoke(null); //passing null to static methods works
            }

        }


    }
}
