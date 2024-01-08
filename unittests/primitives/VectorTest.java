package primitives;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @org.junit.jupiter.api.Test
    void add() {
        // ============ Boundary Partitions Tests ==============
        //test for combination with zero
        Vector zeroVector = new Vector(0.0, 0.0, 0.0);
        Vector vector = new Vector(1.0, 2.0, 3.0);
        assertEquals(vector, zeroVector.add(vector),
                "failed combination with zero vector");
        //test for negative components
        Vector vector1 = new Vector(-1.0, -2.0, -3.0);
        Vector vector2 = new Vector(1.0, 2.0, 3.0);
        assertEquals(new Vector(0.0, 0.0, 0.0), vector1.add(vector2),
                "failed negative plus positive test");
        // max combination test
        Vector vector3 = new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Vector vector4= new Vector(-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE);s
        assertEquals(new Vector(0.0, 0.0, 0.0), vector3.add(vector4),
                "failed max value test combination");
        // test for int plus double
        Vector vectorDouble = new Vector(1.0, 2.0, 3.0);
        Vector vectorInt = new Vector(1, 2, 3);
        assertEquals(new Vector(2,4,6),vectorInt.add(vectorDouble),
                "failed combination of int and double");
        // ============ Equivalence Partitions Tests ==============
        // check add vector inequality
        Vector vectorA = new Vector(2.0, 2.0, 2.0);
        Vector vectorB = new Vector(1.0, 1.0, 2.0);
        assertNotEquals(vectorA.add(vectorB), vectorA, "vector inequality fail test");
       // test Vector Equality
        Vector vectorC  = new Vector(1.0, 2.0, 3.0);
        Vector vectorD  = new Vector(2.0, 4.0, 6.0);
            assertEquals(vectorC.add(vectorC), vectorD,"failed equality add test");


    }

    @org.junit.jupiter.api.Test
    
    void scale() {
        //============ Equivalence Partitions Tests ==============
        //testScale By Positive Scalar
        Vector vector = new Vector(1.0, 2.0, 3.0);
        assertEquals(new Vector(2.0, 4.0, 6.0), vector.scale(2.0),
                "failed scale with positive scalar");
        // ============ Boundary Partitions Tests ==============
        // testScale Zero Vector
        Vector zeroVector = new Vector(0.0, 0.0, 0.0);
        assertEquals(zeroVector, ZeroVector.scale(2),
                "failed zero vector multiplication");
        // multiplication by 0
        assertEquals(new Vector(0, 0, 0), vector.scale(0),
                "failed multiplication by 0");
        // multiplication by negative
        assertEquals(new Vector(-1, -2, -3), vector.scale(-1),
                "failed negative scale");
    }


    @org.junit.jupiter.api.Test
    void dotProduct() {
           // testDotProductStandard
           //============ Equivalence Partitions Tests ==============
            Vector vectorA = new Vector(1.0, 2.0, 3.0);
            Vector vectorB = new Vector(4.0, 5.0, 6.0);
            assertEquals(32.0, vectorA.dotProduct(vectorB), 1e-10,
                    "failed dot product standard test");
            //============ Boundary Partitions Tests ==============
        //testDotProductWithZeroVector
            Vector zeroVector = new Vector(0.0, 0.0, 0.0);
            assertEquals(0.0, vectorA.dotProduct(zeroVector), 1e-10,
                    "failed zero dot product test");
        //testDotProductWithNegativeComponent
            Vector vectorNegative = new Vector(-1.0, -2.0, -3.0);
            Vector vectorPlus = new Vector(4.0, 5.0, 6.0);
            assertEquals(-32.0, vectorPlus.dotProduct(vectorNegative), 1e-10,
                    "failed negative and positive dot product test");
        //testDotProductWithOrthogonalVectors
            Vector vectorX = new Vector(1.0, 0.0, 0.0);
            Vector vectorY = new Vector(0.0, 1.0, 0.0);
            assertEquals(0.0, vectorY.dotProduct(vectorX), 1e-10,
                    "failed Dot Product With Orthogonal Vectors");
        //testDotProductWithSelf
            Vector vector = new Vector(1.0, 2.0, 3.0);
            assertEquals(vector.lengthSquared(), vector.dotProduct(vector), 1e-10,
                    "dot product with self failed");
    }

    @org.junit.jupiter.api.Test
    void crossProduct() {
        //============ Equivalence Partitions Tests ==============
        //testCrossProductStandard
        Vector vectorA = new Vector(1.0, 2.0, 3.0);
        Vector vectorB = new Vector(4.0, 5.0, 6.0);
        assertEquals(new Vector(-3.0, 6.0, -3.0), vectorA.crossProduct(vectorB),
                "failed testCrossProductStandard");
        //============ Boundary Partitions Tests ==============
        //testCrossProductWithZeroVector
            Vector zeroVector = new Vector(0.0, 0.0, 0.0);
            assertEquals(zeroVector, vectorA.crossProduct(zeroVector),
                    "failed test Cross Product With Zero Vector");
       // testCrossProductWithParallelVectors
            Vector parallelVector = new Vector(2.0, 4.0, 6.0); // A scalar multiple of vectorA
            assertEquals(new Vector(0.0, 0.0, 0.0), vectorA.crossProduct(parallelVector),
                    " failed testCrossProductWithParallelVectors");
        //testCrossProductWithOrthogonalVectors
            Vector vectorX = new Vector(1.0, 0.0, 0.0);
            Vector vectorY = new Vector(0.0, 1.0, 0.0);
            assertEquals(new Vector(0.0, 0.0, 1.0), vectorY.crossProduct(vectorX),
                    "failed testCrossProductWithOrthogonalVectors");
        //testCrossProductWithSelf
            assertEquals(new Vector(0.0, 0.0, 0.0), vector.crossProduct(vectorA),
                    "failed testCrossProductWithSelf");
        //testCrossProductWithNegativeComponents
            Vector vectorNegative = new Vector(1.0, -2.0, 3.0);
            Vector vectorNegativeA= new Vector(-4.0, 5.0, -6.0);
            assertEquals(new Vector(-27.0, -6.0, -3.0),
                    vectorNegativeA.crossProduct(vectornegative),
                    "testCrossProductWithNegativeComponents");
    }

    @org.junit.jupiter.api.Test
    void lengthSquared() {
        //============ Equivalence Partitions Tests ==============
       // testLengthSquaredStandard
        Vector vector = new Vector(1.0, 2.0, 3.0);
        assertEquals(14.0, vector.lengthSquared(), 1e-10,
                "failed testLengthSquaredStandard");
        //============ Boundary Partitions Tests ==============
        // testLengthSquaredWithZeroVector
            Vector zeroVector = new Vector(0.0, 0.0, 0.0);
            assertEquals(0.0, zeroVector.lengthSquared(), 1e-10,
                    " failed testLengthSquaredWithZeroVector");
        //testLengthSquaredWithNegativeComponents
            Vector vectorNegative = new Vector(-1.0, -2.0, -3.0);
            assertEquals(14.0, vector.lengthSquared(), 1e-10,
                    "failed testLengthSquaredWithNegativeComponents"); // Squared magnitude is the same regardless of signs

    }

    @org.junit.jupiter.api.Test
    void length() {
        //============ Equivalence Partitions Tests ==============
        //testLengthStandard
            Vector vector = new Vector(1.0, 2.0, 3.0);
            assertEquals(Math.sqrt(14.0), vector.length(), 1e-10,
                    "failed testLengthStandard");
        //============ Boundary Partitions Tests ==============
       // testLengthWithZeroVector
            Vector zeroVector = new Vector(0.0, 0.0, 0.0);
            assertEquals(0.0, zeroVector.length(), 1e-10,
                    "failed testLengthWithZeroVector");
       // testLengthWithNegativeComponents
            Vector vectorNegative = new Vector(-1.0, -2.0, -3.0);
            assertEquals(Math.sqrt(14.0), vectorNegative.length(), 1e-10,
                    "failed testLengthWithNegativeComponents");

    }

    @org.junit.jupiter.api.Test
    void normalize() {
        //============ Equivalence Partitions Tests ==============
       //testNormalizeStandard
        Vector vector = new Vector(0.0, 4.0, 3.0);
        assertEquals(new Vector(3.0 /5, 4.0 / 5, 0.0), vector.normalize(),
                " failed testNormalizeStandard");
        //============ Boundary Partitions Tests ==============
        // testNormalizeZeroVector
            Vector zeroVector = new Vector(0.0, 0.0, 0.0);
            assertEquals(zeroVector, zeroVector.normalize(),
                    "failed testNormalizeZeroVector");
        //testNormalizeUnitVector
            Vector unitVector = new Vector(1.0, 0.0, 0.0);
            assertEquals(unitVector, unitVector.normalize(),
                    " failed testNormalizeUnitVector" );
        //testNormalizeWithNegativeComponents
            Vector vectorNegative = new Vector(-4.0, 3.0,0 );
            assertEquals(new Vector(-2.0 / 5, 3.0 /5, 0 /5.0),vectorNegative.normalize(),
                    "failed testNormalizeWithNegativeComponents");
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        //============ Equivalence Partitions Tests ==============
        //TC01: test ToString Standard
            Vector vector = new Vector(1.0, 2.0, 3.0);
            assertEquals("(1.0, 2.0, 3.0)", vector.toString());
        //============ Boundary Partitions Tests ==============
        //TC10: testToStringWithZeroVector
            Vector zeroVector = new Vector(0.0, 0.0, 0.0);
            assertEquals("(0.0, 0.0, 0.0)", zeroVector.toString(),
                    "failed test ToString With Zero Vector");

        }

    }

    @org.junit.jupiter.api.Test
    void testEquals() {
    //============ Equivalence Partitions Tests ==============
        //TC01: test Equals Standard
            Vector vectorA = new Vector(1.0, 2.0, 3.0);
            Vector vectorB = new Vector(1.0, 2.0, 3.0);
            assertTrue(vectorA.equals(vectorB),
                    " failed test Equals Standard");
        //TC02:testEqualsWithDifferentComponents
            Vector vectorC = new Vector(3.0, 2.0, 1.0);
            assertFalse(vectorA.equals(vectorC),
                    "failed test Equals With Different Components");
        // TC03:test equals self
        assertFalse( vectorA.equals(vectorA)"failed test doe equal itself");
        //============ Boundary Partitions Tests ==============
        //TC10:testEqualsWithPrecisionTolerance
            Vector vectorANew = new Vector(1.0000001, 2.0000001, 3.0000001);
            assertTrue(vectorA.equals(vectorANew, 1e-6),
                    "failed testEqualsWithPrecisionTolerance");


    }
}