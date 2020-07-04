package io.edukativ.myskoolin.config;

import io.edukativ.myskoolin.MyskoolinApp;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = MyskoolinApp.class)
@ActiveProfiles("it")
@AutoConfigureMockMvc
public class MySkoolinIntegrationTests implements InitializingBean {

    @MockBean
    private Clock clock;

    @Autowired
    private MockMvc mockMvc;

    protected EndPointCaller endPointCaller;

    @BeforeEach
    void setUp() {
        when(clock.instant()).thenReturn(Dates.defaultFixedClock.instant());
        when(clock.getZone()).thenReturn(Dates.zone);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        endPointCaller = new EndPointCaller(mockMvc);
    }

    protected void addRequestParam(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String paramName, List<String> filters) {
        if (!filters.isEmpty()) {
            mockHttpServletRequestBuilder.param(paramName, filters.toArray(new String[0]));
        }
    }

    protected static class Dates {

        public static final ZoneId zone = ZoneId.systemDefault();
        public static final LocalDateTime defaultDateTime = LocalDateTime.of(2020, 4, 10, 10, 10);
        public static final Clock defaultFixedClock = Clock.fixed(defaultDateTime.atZone(zone).toInstant(), zone);
    }

    public static class Users {

        public static final String DEFAULT_ADMINISTRATION_LOGIN = "leboss";
        public static final String DEFAULT_ADMINISTRATION_PASSWORD = "admin";
    }

    public static class EndPoints {

        private static final String BASE_URL = "/api";
        public static final String TIMETABLES_BASE_URL = BASE_URL + "/timetables";
        public static final String TIMETABLES_SOLVE_URL = TIMETABLES_BASE_URL + "/solve";
        public static final String TIMETABLES_SOLVER_STATUS_URL = TIMETABLES_BASE_URL + "/solve/status";
    }

    public static class TestsFolders {

        public static final String TIMETABLES_FOLDER = "timetables";
    }
}
