import org.junit.Assert;
import org.junit.Test;

public class test_ast {
    @Test
    public void test_oneCondition(){

        String expected = "companyname=\"htsc\"";
        Assert.assertEquals(expected, Combine.getCondition("(companyname = \"htsc\")"));
    }

    @Test
    public void test_twoConditions(){

        String expected = "(companyname=\"htsc\") or (age<30)";
        Assert.assertEquals(expected, Combine.getCondition("(companyname = \"htsc\") or (age<30)"));
    }

    @Test
    public void test_multiConditions(){

        String expected = "(companyname=\"htsc\") or ((age<30) and (sex!=\"male\"))";
        Assert.assertEquals(expected, Combine.getCondition("(companyname = \"htsc\") or ((age<30) and (sex!=\"male\"))"));

        expected = "(companyname=\"htsc\") or ((age<30) and ((sex!=\"male\") or (sex==\"female\")))";
        Assert.assertEquals(expected, Combine.getCondition("(companyname = \"htsc\") or ((age<30) and ((sex!=\"male\") or (sex==\"female\")))"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_InvalidLeftbraceConditions(){

        Combine.getCondition("(companyname = \"htsc\") or ((age<30)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_InvalidRightbraceConditions(){

        Combine.getCondition("(companyname = \"htsc\") or (age<30))");
    }

}
