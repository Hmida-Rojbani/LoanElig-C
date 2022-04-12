package de.tekup.loan.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import de.tekup.loan.services.LoanService;
import de.tekup.loan.soap.ws.loaneligebilty.CustomerRequest;
import de.tekup.loan.soap.ws.loaneligebilty.WsResponse;
import lombok.AllArgsConstructor;

@Endpoint
@AllArgsConstructor
public class LoanEndPoint {
	
	public static final String nameSpace = "http://www.tekup.de/loan/soap/ws/loanEligebilty";
	
	private LoanService loanService;
	
	@PayloadRoot(namespace = nameSpace, localPart = "CustomerRequest")
	@ResponsePayload
	public WsResponse checkLoanStatus(@RequestPayload CustomerRequest customerRequest) {
		return loanService.getLoanStatus(customerRequest);
	}

}
