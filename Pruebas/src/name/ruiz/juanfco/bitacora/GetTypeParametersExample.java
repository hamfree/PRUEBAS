package name.ruiz.juanfco;

import java.lang.reflect.TypeVariable;

public class GetTypeParametersExample {

    @SuppressWarnings("rawtypes")
	public static void main(String... args) {
        Class<MyClass> c = MyClass.class;
        TypeVariable<Class<MyClass>>[] typeParameters = c.getTypeParameters();
        for (TypeVariable<Class<MyClass>> typeParameter : typeParameters) {
            System.out.println(typeParameter);
        }
    }

    private static class MyClass<T, S> {}
}