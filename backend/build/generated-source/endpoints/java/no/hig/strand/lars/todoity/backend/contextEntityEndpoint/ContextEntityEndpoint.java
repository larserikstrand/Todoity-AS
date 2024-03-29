/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-10-05 at 22:39:23 UTC 
 * Modify at your own risk.
 */

package no.hig.strand.lars.todoity.backend.contextEntityEndpoint;

/**
 * Service definition for ContextEntityEndpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link ContextEntityEndpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class ContextEntityEndpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.19.0 of the contextEntityEndpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://eighth-alchemy-498.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "contextEntityEndpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public ContextEntityEndpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  ContextEntityEndpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getContextEntity".
   *
   * This request holds the parameters needed by the contextEntityEndpoint server.  After setting any
   * optional parameters, call the {@link GetContextEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public GetContextEntity getContextEntity(java.lang.Long id) throws java.io.IOException {
    GetContextEntity result = new GetContextEntity(id);
    initialize(result);
    return result;
  }

  public class GetContextEntity extends ContextEntityEndpointRequest<no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity> {

    private static final String REST_PATH = "contextentity/{id}";

    /**
     * Create a request for the method "getContextEntity".
     *
     * This request holds the parameters needed by the the contextEntityEndpoint server.  After
     * setting any optional parameters, call the {@link GetContextEntity#execute()} method to invoke
     * the remote operation. <p> {@link GetContextEntity#initialize(com.google.api.client.googleapis.s
     * ervices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetContextEntity(java.lang.Long id) {
      super(ContextEntityEndpoint.this, "GET", REST_PATH, null, no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetContextEntity setAlt(java.lang.String alt) {
      return (GetContextEntity) super.setAlt(alt);
    }

    @Override
    public GetContextEntity setFields(java.lang.String fields) {
      return (GetContextEntity) super.setFields(fields);
    }

    @Override
    public GetContextEntity setKey(java.lang.String key) {
      return (GetContextEntity) super.setKey(key);
    }

    @Override
    public GetContextEntity setOauthToken(java.lang.String oauthToken) {
      return (GetContextEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public GetContextEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetContextEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetContextEntity setQuotaUser(java.lang.String quotaUser) {
      return (GetContextEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetContextEntity setUserIp(java.lang.String userIp) {
      return (GetContextEntity) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetContextEntity setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetContextEntity set(String parameterName, Object value) {
      return (GetContextEntity) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertContextEntity".
   *
   * This request holds the parameters needed by the contextEntityEndpoint server.  After setting any
   * optional parameters, call the {@link InsertContextEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity}
   * @return the request
   */
  public InsertContextEntity insertContextEntity(no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity content) throws java.io.IOException {
    InsertContextEntity result = new InsertContextEntity(content);
    initialize(result);
    return result;
  }

  public class InsertContextEntity extends ContextEntityEndpointRequest<no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity> {

    private static final String REST_PATH = "contextentity";

    /**
     * Create a request for the method "insertContextEntity".
     *
     * This request holds the parameters needed by the the contextEntityEndpoint server.  After
     * setting any optional parameters, call the {@link InsertContextEntity#execute()} method to
     * invoke the remote operation. <p> {@link InsertContextEntity#initialize(com.google.api.client.go
     * ogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param content the {@link no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity}
     * @since 1.13
     */
    protected InsertContextEntity(no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity content) {
      super(ContextEntityEndpoint.this, "POST", REST_PATH, content, no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity.class);
    }

    @Override
    public InsertContextEntity setAlt(java.lang.String alt) {
      return (InsertContextEntity) super.setAlt(alt);
    }

    @Override
    public InsertContextEntity setFields(java.lang.String fields) {
      return (InsertContextEntity) super.setFields(fields);
    }

    @Override
    public InsertContextEntity setKey(java.lang.String key) {
      return (InsertContextEntity) super.setKey(key);
    }

    @Override
    public InsertContextEntity setOauthToken(java.lang.String oauthToken) {
      return (InsertContextEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertContextEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertContextEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertContextEntity setQuotaUser(java.lang.String quotaUser) {
      return (InsertContextEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertContextEntity setUserIp(java.lang.String userIp) {
      return (InsertContextEntity) super.setUserIp(userIp);
    }

    @Override
    public InsertContextEntity set(String parameterName, Object value) {
      return (InsertContextEntity) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeContextEntity".
   *
   * This request holds the parameters needed by the contextEntityEndpoint server.  After setting any
   * optional parameters, call the {@link RemoveContextEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveContextEntity removeContextEntity(java.lang.Long id) throws java.io.IOException {
    RemoveContextEntity result = new RemoveContextEntity(id);
    initialize(result);
    return result;
  }

  public class RemoveContextEntity extends ContextEntityEndpointRequest<Void> {

    private static final String REST_PATH = "contextentity/{id}";

    /**
     * Create a request for the method "removeContextEntity".
     *
     * This request holds the parameters needed by the the contextEntityEndpoint server.  After
     * setting any optional parameters, call the {@link RemoveContextEntity#execute()} method to
     * invoke the remote operation. <p> {@link RemoveContextEntity#initialize(com.google.api.client.go
     * ogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveContextEntity(java.lang.Long id) {
      super(ContextEntityEndpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveContextEntity setAlt(java.lang.String alt) {
      return (RemoveContextEntity) super.setAlt(alt);
    }

    @Override
    public RemoveContextEntity setFields(java.lang.String fields) {
      return (RemoveContextEntity) super.setFields(fields);
    }

    @Override
    public RemoveContextEntity setKey(java.lang.String key) {
      return (RemoveContextEntity) super.setKey(key);
    }

    @Override
    public RemoveContextEntity setOauthToken(java.lang.String oauthToken) {
      return (RemoveContextEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveContextEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveContextEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveContextEntity setQuotaUser(java.lang.String quotaUser) {
      return (RemoveContextEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveContextEntity setUserIp(java.lang.String userIp) {
      return (RemoveContextEntity) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveContextEntity setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveContextEntity set(String parameterName, Object value) {
      return (RemoveContextEntity) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateContextEntity".
   *
   * This request holds the parameters needed by the contextEntityEndpoint server.  After setting any
   * optional parameters, call the {@link UpdateContextEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity}
   * @return the request
   */
  public UpdateContextEntity updateContextEntity(no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity content) throws java.io.IOException {
    UpdateContextEntity result = new UpdateContextEntity(content);
    initialize(result);
    return result;
  }

  public class UpdateContextEntity extends ContextEntityEndpointRequest<no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity> {

    private static final String REST_PATH = "contextentity";

    /**
     * Create a request for the method "updateContextEntity".
     *
     * This request holds the parameters needed by the the contextEntityEndpoint server.  After
     * setting any optional parameters, call the {@link UpdateContextEntity#execute()} method to
     * invoke the remote operation. <p> {@link UpdateContextEntity#initialize(com.google.api.client.go
     * ogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param content the {@link no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity}
     * @since 1.13
     */
    protected UpdateContextEntity(no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity content) {
      super(ContextEntityEndpoint.this, "PUT", REST_PATH, content, no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity.class);
    }

    @Override
    public UpdateContextEntity setAlt(java.lang.String alt) {
      return (UpdateContextEntity) super.setAlt(alt);
    }

    @Override
    public UpdateContextEntity setFields(java.lang.String fields) {
      return (UpdateContextEntity) super.setFields(fields);
    }

    @Override
    public UpdateContextEntity setKey(java.lang.String key) {
      return (UpdateContextEntity) super.setKey(key);
    }

    @Override
    public UpdateContextEntity setOauthToken(java.lang.String oauthToken) {
      return (UpdateContextEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateContextEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateContextEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateContextEntity setQuotaUser(java.lang.String quotaUser) {
      return (UpdateContextEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateContextEntity setUserIp(java.lang.String userIp) {
      return (UpdateContextEntity) super.setUserIp(userIp);
    }

    @Override
    public UpdateContextEntity set(String parameterName, Object value) {
      return (UpdateContextEntity) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link ContextEntityEndpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link ContextEntityEndpoint}. */
    @Override
    public ContextEntityEndpoint build() {
      return new ContextEntityEndpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link ContextEntityEndpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setContextEntityEndpointRequestInitializer(
        ContextEntityEndpointRequestInitializer contextentityendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(contextentityendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
