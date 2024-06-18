package com.BookMyShow.Ticket.theatre;

import com.BookMyShow.Ticket.Exceptions.BusinessException;
import com.BookMyShow.Ticket.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Service
@RequiredArgsConstructor
public class ShowClient {
    @Value("${application.config.theatre-url}")
    private String theatreUrl;

    private final RestTemplate restTemplate;

    public ShowResponse fillShowTickets(Map<Integer, List<String>> requestBody) throws BusinessException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Map<Integer, List<String>>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<ShowResponse> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<ShowResponse> responseEntity = restTemplate.exchange(
                theatreUrl + "/shows/fillTickets",
                POST,
                requestEntity,
                responseType
        );
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("An error occurred while processing the show request : " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }

    public List<ShowSeatResponse> getShowSeats() throws BusinessException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<ShowSeatResponse>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<ShowSeatResponse>> responseEntity = restTemplate.exchange(
                theatreUrl + "/shows/showSeats",
                GET,
                requestEntity,
                responseType
        );
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("An error occurred while processing the show request : " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }

}
