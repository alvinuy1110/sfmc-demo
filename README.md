# sfmc-demo


## Authentication

https://developer.salesforce.com/docs/atlas.en-us.noversion.mc-apis.meta/mc-apis/authenticate-soap-api.htm

Will use OAuth2


## Certificates

When dealing with SSL (i.e. https), need to import the CA in truststore.

1) Get the trust CA
```
echo -n | openssl s_client -connect mclgchpch13g9zxmt47b-2xyntt4.soap.marketingcloudapis.com:443 -showcerts
```
2) Copy the CA into file

3) Put in truststore.  For example the pem file is "rootca.pem"
```
  openssl x509 -in rootca.pem -inform pem -out rootca.der -outform der
    keytool -v -printcert -file rootca.der
    keytool -importcert -alias sfmcca -keystore sfmctrust -storepass changeit -file rootca.der
```


## SOAP

WSDL Link for Your Tenant
All instances: https://YOUR_SUBDOMAIN.soap.marketingcloudapis.com/etframework.wsdl

SOAP API with Your Tenant's Endpoints
All instances: https://YOUR_SUBDOMAIN.soap.marketingcloudapis.com/Service.asmx


### CXF

https://developer.salesforce.com/docs/atlas.en-us.noversion.mc-apis.meta/mc-apis/connecting_to_the_soap_api_using_java_and_cxf.htm

### WSDL

Note: This is only needed if the CAs are NOT imported in truststore

The existing "etframework.wsdl" has an import to an "https" xsd causing failure.  To circumvent for now:

1) Create a file "etframework.wsdl" in classpath to override the one in the sdk
2) Modify the file to remove the import and manually add to custom wsdl

