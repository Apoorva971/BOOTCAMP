package healthycoderapp;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {


    @Test
    void dietIsRecmmended(){
        assertTrue(true);

        //given
        double weight=70.0;
        double height=9.32;

        //when
        boolean recommended=BMICalculator.isDietRecommended(weight,height);

        //then
        assertTrue(recommended);

    }

    @Test
    void shouldReturnCoderWithWorstBMIWhenListIsNotEmpty()
    {
        //given
        List<Coder> list=new ArrayList<>();
        list.add(new Coder(1.80,60));
        list.add(new Coder(1.82,98.0));
        list.add(new Coder(1.82,64.7));

        //When
        Coder codercalculate=BMICalculator.findCoderWithWorstBMI(list);

        //then
        assertAll(
                ()->assertEquals(1.82,codercalculate.getHeight()),
                ()->assertEquals(98.0,codercalculate.getWeight())

        );

    }
    @Test
    void shouldReturnNullWithWorstBMIWhenListIsEmpty()
    {
        //given
        List<Coder> list=new ArrayList<>();

        //When
        Coder codercalculate=BMICalculator.findCoderWithWorstBMI(list);

        //then
        assertNull(codercalculate);
    }

    @Test
    void shouldReturnCorrectBMIScoreArrrayWhenListIsNotEmpty()
    {
        //given
        List<Coder> list=new ArrayList<>();
        list.add(new Coder(1.80,60));
        list.add(new Coder(1.82,98.0));
        list.add(new Coder(1.82,64.7));
        double []expected={18.52 ,29.59 ,19.53};

        //When
        double bmiScores[]=BMICalculator.getBMIScores(list);

        //then
        assertArrayEquals(expected,bmiScores);
    }
}
   /* void should_returntrue_when_deightrecommended() {
        //given
        double weight = 89.0;
        double height = 1.72;
        //when
        boolean recommended = BMICalculator.isDietRecommended(weight,height);
        //then
        assertTrue(recommended);
    }
    void should_returnfalse_when_deightrecommended() {
        //given
        double weight = 40.0;
        double height = 1.92;
        //when
       // boolean recommended = BMICalculator.isDietRecommended(weight,height);
       // Executable executable = ()-> BMICalculator.isDietRecommended(weight,height);
        //then
      //  assertFalse(recommended);
        assertThrows(ArithmeticException.class,executable);
    }

*/