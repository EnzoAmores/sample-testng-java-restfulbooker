package utilities;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import static utilities.extentreports.ExtentTestManager.getTest;

public class RestFilter implements Filter {
    /*
     * Overriden this method for the purpose of catching the request and response
     * and putting it in the Extent Report.
     * Will only be produced if the class is initialized and including it in the
     * .filter() while building the request specs in the other methods.
     */
    @Override
    public Response filter(FilterableRequestSpecification requestSpecification,
            FilterableResponseSpecification responseSpecification, FilterContext filterContext) {
        Response response = filterContext.next(requestSpecification, responseSpecification);

        if (getTest() != null) {
            getTest().log(Status.INFO, createRequestDetails(requestSpecification));
            getTest().log(Status.INFO, createResponseDetails(response));
        }

        return response;
    }

    /*
     * Create Request Details
     * Fitted for appearing a bit pretty in Extent Reports .html report file hence
     * the HTML tags.
     */
    private String createRequestDetails(FilterableRequestSpecification request) {
        StringBuilder requestDetails = new StringBuilder();

        requestDetails.append("<ul>Request Details:");
        requestDetails.append(String.format("<li>URI: %s</li>", request.getURI()));
        requestDetails.append(String.format("<li>Method: %s</li>", request.getMethod()));

        if (request.getHeaders().size() > 0) {
            requestDetails.append("<li>Headers:<ul>");
            request.getHeaders().asList().forEach(
                    h -> requestDetails.append(String.format("<li>%s</li>", h.toString().replaceFirst("=", ": "))));
            requestDetails.append("</ul></li>");
        }

        if (request.getBody() != null)
            requestDetails.append(
                    String.format("<li>Body:</li>%s", jsonPrettyFormatter(request.getBody()).replace("\n", "<br />")));

        requestDetails.append("</ul>");

        return requestDetails.toString();
    }

    /*
     * Create Response Details
     * Fitted for appearing a bit pretty in Extent Reports .html report file hence
     * the HTML tags.
     */
    private String createResponseDetails(Response response) {
        StringBuilder responseDetails = new StringBuilder();

        responseDetails.append("<ul>Response Details:");
        responseDetails.append(String.format("<li>Code: %s</li>", response.getStatusCode()));

        if (response.getBody() != null)
            responseDetails
                    .append(String.format("<li>Body:</li>%s", response.asPrettyString().replace("\n", "<br />")));

        responseDetails.append("</ul>");

        return responseDetails.toString();
    }

    private String jsonPrettyFormatter(String uglyJsonString) {
        String prettyJsonString = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object jsonObject = objectMapper.readValue(uglyJsonString, Object.class);

            prettyJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (JsonMappingException e) {
            Log.error(e.getMessage());
        } catch (JsonProcessingException e) {
            Log.error(e.getMessage());
        }

        return prettyJsonString;
    }
}