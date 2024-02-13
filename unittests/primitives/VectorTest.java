package primitives;


import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;


class VectorTest {

    /**
     * Test case for tube
     * {@link primitives.Vector#add(Vector)}
     */

    @org.junit.jupiter.api.Test
    void add() {

        // ============ Boundary Partitions Tests ==============

        //TC01: test for addition with self
        Vector vector0 = new Vector(1.0, 2.0, 3.0);
        Vector expectedSolution = new Vector(2, 4, 6);
        assertEquals(expectedSolution, vector0.add(vector0),
                "Addition with self failed");

        //TC02: test for addition with the opposite vector (it's coordinates are minus our vector)
        Vector vector1=new Vector(-1,-2,-3);
        assertThrows(IllegalArgumentException.class, () -> vector1.add(vector0), "Vector equals 0");


        // ============ Equivalence Partitions Tests ==============

        //TC10: check add vector inequality

        Vector vectorA = new Vector(2.0, 2.0, 2.0);
        Vector vectorB = new Vector(1.0, 1.0, 2.0);
        assertNotEquals(vectorA.add(vectorB), vectorA, "vector inequality fail test");

        //TC11: test Vector Equality

        Vector vectorD = new Vector(2.0, 4.0, 6.0);
        assertEquals(vector0.add(vector0), vectorD, "failed equality add test");

        //TC12: Test if Vector addition goes both ways
        assertEquals(vectorA.add(vectorB), vectorB.add(vectorA), "Vector addition doesn't go both ways");
    }

    /**
     * Test case for
     * {@link primitives.Vector#scale(double)}
     */

    @org.junit.jupiter.api.Test
    void scale() {

        //============ Equivalence Partitions Tests ==============


        //TC01: test Scale By Positive Scalar

        Vector vector0 = new Vector(1.0, 2.0, 3.0);
        assertEquals(new Vector(2.0, 4.0, 6.0), vector0.scale(2.0),
                "failed scale with positive scalar");


        //TC02: multiplication by negative
        assertEquals(new Vector(-1, -2, -3), vector0.scale(-1),
                "failed negative scale");


        // ============ Boundary Partitions Tests ==============


        //TC10: test Scale by 1

        assertEquals(vector0.scale(1), vector0, "Vector scale by 1 doesn't work");


        //TC11: test scale by 0

        assertThrows(IllegalArgumentException.class, () -> vector0.scale(0), "Vector equals 0");


    }

    /**
     * Test case for
     * {@link primitives.Vector#dotProduct(Vector)}
     */


    @org.junit.jupiter.api.Test
    void dotProduct() {

        //============ Equivalence Partitions Tests ==============

        //TC01: testDotProductStandard

        Vector vectorA = new Vector(1.0, 2.0, 3.0);
        Vector vectorB = new Vector(4.0, 5.0, 6.0);
        assertEquals(32.0, vectorA.dotProduct(vectorB), 1e-10,
                "failed dot product standard test");


        //TC02: testDotProductWithNegativeComponent

        Vector vectorNegative = new Vector(-1.0, -2.0, -3.0);
        Vector vectorPlus = new Vector(4.0, 5.0, 6.0);
        assertEquals(-32.0, vectorPlus.dotProduct(vectorNegative), 1e-10,
                "failed negative and positive dot product test");


        //============ Boundary Partitions Tests ==============


        //TC10: testDotProductWithOrthogonalVectors

        Vector vectorX = new Vector(1.0, 0.0, 0.0);
        Vector vectorY = new Vector(0.0, 1.0, 0.0);
        assertEquals(0.0, vectorY.dotProduct(vectorX), 1e-10,
                "failed Dot Product With Orthogonal Vectors");


        //TC11: testDotProductWithSelf

        assertEquals(vectorA.lengthSquared(), vectorA.dotProduct(vectorA), 1e-10,
                "dot product with self failed");


        //TC12: Test dot product with opposite vector
       assertEquals(-14,vectorA.dotProduct(vectorNegative),"dot product with opposite vector failed");


    }

    /**
     * Test case for
     * {@link primitives.Vector#crossProduct(Vector)}
     */

    @org.junit.jupiter.api.Test
    void crossProduct() {

        //============ Equivalence Partitions Tests ==============

        //TC01: testCrossProductStandard

        Vector vectorA = new Vector(1.0, 2.0, 3.0);
        Vector vectorB = new Vector(4.0, 5.0, 6.0);
        assertEquals(new Vector(-3.0, 6.0, -3.0), vectorA.crossProduct(vectorB),
                "failed testCrossProductStandard");


        //TC02: testCrossProductWithNegativeComponents

        Vector vectorNegative = new Vector(1.0, -2.0, 3.0);
        Vector vectorNegativeA = new Vector(-4.0, 5.0, -6.0);
        assertEquals(new Vector(-3.0, -6.0, -3.0),
                vectorNegative.crossProduct(vectorNegativeA),
                "testCrossProductWithNegativeComponents");


        //============ Boundary Partitions Tests ==============


        //TC10: testCrossProductWithOrthogonalVectors

        Vector vectorX = new Vector(1.0, 0.0, 0.0);
        Vector vectorY = new Vector(0.0, 1.0, 0.0);
        assertEquals(new Vector(0.0, 0.0, 1.0), vectorX.crossProduct(vectorY),
                "failed testCrossProductWithOrthogonalVectors");


        //TC11: test cross product with self

        assertThrows(IllegalArgumentException.class, () -> vectorX.crossProduct(vectorX), "Vector equals 0");


    }

    /**
     * Test case for
     * {@link Vector#lengthSquared()}
     */

    @org.junit.jupiter.api.Test
    void lengthSquared() {

        //============ Equivalence Partitions Tests ==============

        //TC01: test Length Squared Standard

        Vector vector = new Vector(1.0, 2.0, 3.0);
        assertEquals(14.0, vector.lengthSquared(), 1e-10,
                "failed testLengthSquaredStandard");


        //TC02: test length squared with negative components

        assertEquals(14.0, vector.lengthSquared(), 1e-10,
                "failed testLengthSquaredWithNegativeComponents"); // Squared magnitude is the same regardless of signs


        //============ Boundary Partitions Tests ==============

    }


    /**
     * Test case for
     * {@link Vector#length()}
     */
    @org.junit.jupiter.api.Test
    void length() {

        //============ Equivalence Partitions Tests ==============


        //TC01: test Length Standard

        Vector vector = new Vector(1.0, 2.0, 3.0);
        assertEquals(Math.sqrt(14.0), vector.length(), 1e-10,
                "failed testLengthStandard");


        //TC02: testLengthWithNegativeComponents

        Vector vectorNegative = new Vector(-1.0, -2.0, -3.0);
        assertEquals(Math.sqrt(14.0), vectorNegative.length(), 1e-10,
                "failed testLengthWithNegativeComponents");


        //============ Boundary Partitions Tests ==============

    }


    /**
     * Test case for
     * {@link Vector#normalize()}
     */
    @org.junit.jupiter.api.Test
    void normalize() {

        //============ Equivalence Partitions Tests ==============

        //TC01: testNormalizeStandard

        Vector vector = new Vector(0.0, 4.0, 3.0);
        assertEquals(new Vector(0, 4.0 / 5, 3.0 / 5), vector.normalize(),
                " failed testNormalizeStandard");

        //TC02: testNormalizeWithNegativeComponents

        Vector vectorNegative = new Vector(-4.0, 3.0, 0);
        assertEquals(new Vector(-4.0 / vectorNegative.length(), 3.0 / vectorNegative.length(), 0 / vectorNegative.length()), vectorNegative.normalize(),
                "failed testNormalizeWithNegativeComponents");


        //============ Boundary Partitions Tests ==============

        //TC10: testNormalizeUnitVector

        Vector unitVector = new Vector(1.0, 0.0, 0.0);
        assertEquals(unitVector, unitVector.normalize(),
                " failed testNormalizeUnitVector");

    }

    /**
     * Test method for
     * {@link primitives.Vector#Vector(double, double, double)}
     */


    void Testconstructor() {

        //============ Equivalence Partitions Tests ==============


        //============ Boundary Partitions Tests ==============

        assertThrows(IllegalArgumentException.class, () -> new Vector(0,0,0), "Didn't throw Vector equals 0");

    }

    /**
     * Test method for
     * {@link primitives.Vector#Vector(Double3)}
     */

    void TestconstructorDouble3() {

        //============ Equivalence Partitions Tests ==============


        //============ Boundary Partitions Tests ==============

        Double3 temp=new Double3(0,0,0);
        assertThrows(IllegalArgumentException.class, () -> new Vector(temp), "Didn't throw Vector equals 0");

    }
}