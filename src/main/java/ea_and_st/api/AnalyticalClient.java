package ea_and_st.api;

import com.ea_and_st.utils.annotations.MySubLogger;
import com.ea_and_st.utils.configs.AnalyticalServerConfig;
import com.ea_and_st.utils.enums.ApiSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticalClient {
    private final int LIMITS_IN_LIST = 20;
    private final AnalyticalServerConfig analyticalServerConfig;

    @MySubLogger(source = ApiSource.ANALYTICAL)
    public List<Long> getPlaceForYouWithUsingUserId(Long userId) {
        return getIdsFromAnalyticalServer("place_for_you?id=" + userId);
    }

    @MySubLogger(source = ApiSource.ANALYTICAL)
    public List<Long> getPopularPlaces() {
        return getIdsFromAnalyticalServer("popular_places");
    }

    @MySubLogger(source = ApiSource.ANALYTICAL)
    public List<Long> getNonPopularPlacesWithUsingUserId(Long userId) {
        return getIdsFromAnalyticalServer("non_popular_places?id=" + userId);
    }

    @MySubLogger(source = ApiSource.ANALYTICAL)
    public List<Long> getSmartPlacesWithUsingUserId(Long userId) {
        return getIdsFromAnalyticalServer("smart_place_for_you?id=" + userId);
    }

    private List<Long> getIdsFromAnalyticalServer(String resourcePathAndQuery) {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Long[]> responseEntity;
        responseEntity = restTemplate.getForEntity(
                analyticalServerConfig.getPath() + resourcePathAndQuery,
                Long[].class
        );
        return Arrays.stream(Objects.requireNonNull(responseEntity.getBody()))
                .limit(LIMITS_IN_LIST)
                .collect(Collectors.toList());
    }
}
