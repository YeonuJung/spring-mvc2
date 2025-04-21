package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void stringToInteger(){
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer result = stringToIntegerConverter.convert("10");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString(){
        IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
        String result = integerToStringConverter.convert(10);
        Assertions.assertThat(result).isEqualTo("10");
    }

    @Test
    void ipPortToString(){
        IpPortToStringConverter ipPortToStringConverter = new IpPortToStringConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        String ipPortToString = ipPortToStringConverter.convert(ipPort);
        Assertions.assertThat(ipPortToString).isEqualTo("127.0.0.1:8080");
    }

    @Test void StringToIpPort(){
        StringToIpPortConverter stringToIpPortConverter = new StringToIpPortConverter();
        IpPort ipPort = stringToIpPortConverter.convert("127.0.0.1:8080");
        Assertions.assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
