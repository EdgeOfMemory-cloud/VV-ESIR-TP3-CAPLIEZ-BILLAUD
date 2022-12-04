# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer (Louis-Gabriel CAPLIEZ et Valère BILLAUD, ESIR2 Spé INFO, option SI)

Fichier *DetachedTestCase* : Un cas de test doit faire partie d'une classe de test, être publique, retourner void, et ne prend pas de paramêtre. Elle doit être annotée avec @Test. Si elle doit être ignorée, utiliser @Test et @Ignore. Si c'est une sous-routine d'un cas de test, elle doit être privée.

Fichier *JUnit4SuitesShouldUseSuiteAnnotation* : différence entre JUnit 3 et 4. Dans cette dernière, plus besoin d'utiliser la méthode suite() mais les annotations @RunWith et @SuiteClasses.

Fichier *JUnit4TestShouldUseAfterAnnotation* : différence entre JUnit 3 et 4. Dans cette dernière plus besoin d'utiliser la méthode tearDown() pour défaire le monde après chaque test, mais annoter la méthode correspondante avec @After.

Fichier *JUnit4TestShouldUseBeforeAnnotation* : même idée mais avant chaque cas de test pour le constructeur/setUp() et l'annotation @Before.

Fichier *JUnit4TestShouldUseTestAnnotation* : en JUnit 3, les tests unitaires exécutés devaient contenir au début "test" dans leur nom. En JUnit 4, cette règle est délaissée au profit de l'annotation @Test.

Fichier *JUnitAssertionsShouldIncludeMessage* : toutes les assertions ont une version à trois paramètres permettant d'affichier un message lors de l'exécution des tests. Il faut s'efforcer de les utiliser pour transmettre des messages, informatifs de préférence.

Fichier *JUnitSpelling* : attention JUnit 3 est sensible à la casse. setUp() != setup(), auquel cas la fonction ne sera pas exécutée ! (PS : JUnit 4 est bien sinon =))

Fichier *JUnitStaticSuite* : en JUnit 3, pour que la méthode suite() soit exécutée, il faut qu'soit publique et statique.

Fichier *JUnitTestContainsTooManyAsserts* : en JUnit 4, 5 et TestNG Tests, il faut que le nombre d'asserts dans un cas de test soit petit (limite à définir) pour pouvoir améliorer sa compréhension et au besoin comprendre l'erreur trouvée. Si plusieurs assertions sont nécessaires, découpées en plusieurs tests unitaires/scénarios.

Fichier *JUnitTestsShouldIncludeAssert* : complémentaire de la règle précédente, un cas de test sans assertion ne teste pas grand chose et n'aide pas la compréhension. Il faut qu'il y en ait un.

Fichier *JUnitUseExpected* : si le test doit lever une exception, la signaler avec l'annotation @Test(expected) pour améliorer la compréhension et la qualité des tests.

Fichier *UnnecessaryBooleanAssertion* : les assertions comme assertTrue(true) et assertFalse(false) sont inutiles car elles passeront toujours. Elles n'apportent aucune information. Penser à reformater le test (utilisation de fail() en cas d'erreur par exemple) pour mieux correspondre à vos besoins.

Fichier *UseAssertEqualsInsteadOfAssertTrue* : une méthode assertEquals() a été créée exprès pour tester l'égalité profonde entre deux objets, utiliser celle-ci plutôt que assertTrue(objectA.equals(objetctB)).

Fichier *UseAssertNullInsteadOfAssertTrue* : même idée que précédemment, pour comparer une référence égale à null ou non, utiliser plutôt assertNull(objectA) ou assertNotNull(objectA) que assertTrue(objectA == null).

Fichier *UseAssertSameInsteadOfAssertTrue* : dans la continuité, pour tester si deux références pointent sur le même objet, utiliser les méthodes assertSame(objectA, objectB) et assertNotSame(objectA, objectB) que assertTrue(objectA == objectB) ou assertTrue(objectA != objectB).

Fichier *UseAssertTrueInsteadOfAssertEquals* : utiliser assertTrue() ou assertFalse() pour comparer la valeur d'un booléen ou d'une instance de la classe Boolean que la méthode assertEquals(). Elles sont là pour cela et améliorent la lisiblité =).

Dans le projet [Apache Commons Lang](https://github.com/apache/commons-lang/blob/master/), le fichier [AnnotationUtilsTest.java](https://github.com/apache/commons-lang/blob/master/src/test/java/org/apache/commons/lang3/AnnotationUtilsTest.java), ligne 415 possède le test suivant :

```java

    /****/
    @Test
    public void testEquivalence() {
        assertTrue(AnnotationUtils.equals(field1.getAnnotation(TestAnnotation.class), field2.getAnnotation(TestAnnotation.class)));
        assertTrue(AnnotationUtils.equals(field2.getAnnotation(TestAnnotation.class), field1.getAnnotation(TestAnnotation.class)));
    }
    /****/
```
délenche via PMD la régle *JUnit assertions should include a message* vue dans le fichier *JUnitAssertionsShouldIncludeMessage*. En effet le test `java assertTrue(AnnotationUtils.equals(field1.getAnnotation(TestAnnotation.class), field2.getAnnotation(TestAnnotation.class)));` ne possède pas de message explicite décrivant ce qui est comparé. Il pourrait être modifié comme suit pour améliorer sa compréhension :

```java


        assertTrue("Equivalence between the result of AnnotationUtils.equals(field1.getAnnotation(TestAnnoatation.class)) and field2.getAnnotation(TestAnnotation.class).",AnnotationUtils.equals(field1.getAnnotation(TestAnnotation.class), field2.getAnnotation(TestAnnotation.class)));
```