# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer (Louis-Gabriel CAPLIEZ et Valère BILLAUD, ESIR2 Spé INFO, option SI)

1. La représentation informatique des nombres flottants sur 32/64 bits ou plus implique l'utilisation d'arrondi à chaque opération. Dans le cas `assertTrue(3 * .4 == 1.2)`, 3 * .4 est d'abord évalué puis arrondi avant d'être comparé à 1.2. Or cet arrondi fait que le résulat de la première opération n'est pas exactement égal à 1.2. D'où l'échec du test.
Pour réussir, il faut autoriser un léger delta. L'assertion à utiliser de l'API JUnit est `assertEquals(3 * .4, 1.2, 0.1)` par exemple, *0.1* correspondant au delta. 

2. En Java, `assertEquals()` équivaut à la fonction `.equals()` pour comparer le contenu de deux objets. S'ils s'ont de même classe, avec le même contenu, le test passe, sinon il échoue.
A l'inverse, `assertSame()` équivaut à l'opérateur `==` pour des objets, le test passe si les deux références pointent sur le même objet, sans se préoccuper du contenu.

Soit le code Java suivant :

```java

    @Test
    public void testSameTrue() {
        String s1 = new String("Aegis");
        String s2 = s1;
        assertSame(s1, s2); // Passe car s2 = s1 donne la même référence à "Aegis".
    } 

    @Test
    public void testEqualsTrue() {
        String s1 = new String("Aegis");
        String s2 = s1;
        assertEquals(s1, s2); // Passe car le contenu de l'objet pointé par s1 est le même que celui pointé par s2.
    }
```
cependant :

```java

    @Test
    public void testSameFalse() {
        String s1 = new String("Aegis");
        String s2 = new String("Aegis");
        assertSame(s1, s2); // Ne passe pas car s1 ne référence pas le même objet que s1.
    } 

    @Test
    public void testEqualsStillTrue() {
        String s1 = new String("Aegis");
        String s2 = new String("Aegis");
        assertEquals(s1, s2); // Passe car le contenu de l'objet pointé par s1 est le même que celui pointé par s2.
    }
```
3. La source utilisée pour cette question est la [suivante](https://www.baeldung.com/junit-fail).

L'assertion `fail()` peut être aussi utilisée dans d'autres cas comme par exemple un test incomplet :

```java

    @Test
    public void testPasComplet() {
        fail("Vous ne passerez pas !!!");
    }
```
mais aussi pour signaler une exception qui s'est produite sur du code qui n'aurait pas du en lever :

```java

    @Test
    public void testCodeSur() {
        try {
            jeSuisNormalementSur();
        } catch (Exception e) {
             fail("La méthode jeSuisNormalementSur() ne l'est pas tellement.");
        }
    }
```
Nous pouvons aussi penser à signaler une condition non satisfaite : 

```java

    @Test
    public void testDivisionParZero() {
        
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int den = sc.nextInt();
        if (den == 0) {
            fail("Division par zéro est une hérésie !");
        }
    }
```

ainsi qu'une méthode atteignant un return/break beaucoup trop tard :

```java

    @Test
    public void testReturnTardif() {
        
        Scanner sc = new Scanner(System.in);
        int tab = new int[100];
        int cpt = 0;
        for (int i = 0; i < 100; i++) {
            int newNb = sc.nextInt();
            if (newNb >= 0) {
                tab[i] = newNb;
                cpt++;
            }
            if (i == 99) {
                if (cpt == 100) {
                    return;
                }
            }
        }
        fail("Vous avez voulu rentre un nombre négatif dans le tableau, il n'est donc pas complet. Veuillez réessayer.");
    }
```
4. La source utilisée pour cette question est la [suivante](https://howtodoinjava.com/junit5/expected-exception-example/).
En JUnit 4, l'annotation @Test nécessitait de préciser le type **précis** de l'exception voulue. Si elle était levée pendant le test, alors il passait. 

En JUnit 5, la méthode assertThrows() permet plus de souplesse. Elle peut prendre en paramètre une lambda expression qui pourrait lancer une exception. Si aucune exception n'est levée, alors le test ne passera pas comme en JUnit 4. 
Si elle est du même type que celle attendue, le test passera comme en JUnit 4. 
Cependant si l'exception levée est une instance d'une classe fille de l'exception attendue, le test passera aussi, contrairement à JUnit 4. 
Nous pensons que cette nouvelle manière permet d'écrire moins de cas de test pour différencier les différents types d'exception reçus. Elle permet aussi d'être plus souple dans la gestion des erreurs avec les blocs try{} et catch(){} en écrivant moins de code. De plus elle intègre par défaut l'utilisation de lambdas expressions. 