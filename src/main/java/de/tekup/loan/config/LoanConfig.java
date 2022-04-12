package de.tekup.loan.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import de.tekup.loan.endpoints.LoanEndPoint;

@EnableWs
@Configuration
public class LoanConfig {
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> 
				getmessageDispatcher(ApplicationContext context){
		MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet();
		dispatcherServlet.setApplicationContext(context);
		dispatcherServlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean<MessageDispatcherServlet>(dispatcherServlet);
	}
	
	@Bean
	public XsdSchema schema() {
		return new SimpleXsdSchema(new ClassPathResource("loanEligebilty.xsd"));
	}
	
	@Bean("loan")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setSchema(schema);
		wsdl11Definition.setPortTypeName("LoanServiceIndicator");
		
		wsdl11Definition.setTargetNamespace(LoanEndPoint.nameSpace);
		wsdl11Definition.setLocationUri("/ws");
		
		return wsdl11Definition;
	}

}
