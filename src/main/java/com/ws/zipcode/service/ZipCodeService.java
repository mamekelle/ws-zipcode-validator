package zipcode.service;

import com.ws.zipcode.domain.Pair;

import java.util.List;

public interface ZipCodeService {
    List<Pair> restrictedZipRange(List<Pair> inputZipRange);
}
