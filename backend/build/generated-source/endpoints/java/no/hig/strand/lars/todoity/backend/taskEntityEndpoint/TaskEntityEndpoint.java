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
 * on 2014-10-05 at 22:39:22 UTC 
 * Modify at your own risk.
 */

package no.hig.strand.lars.todoity.backend.taskEntityEndpoint;

/**
 * Service definition for TaskEntityEndpoint (v1).
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
 * This service uses {@link TaskEntityEndpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class TaskEntityEndpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.19.0 of the taskEntityEndpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "taskEntityEndpoint/v1/";

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
  public TaskEntityEndpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  TaskEntityEndpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getTaskEntity".
   *
   * This request holds the parameters needed by the taskEntityEndpoint server.  After setting any
   * optional parameters, call the {@link GetTaskEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public GetTaskEntity getTaskEntity(java.lang.String id) throws java.io.IOException {
    GetTaskEntity result = new GetTaskEntity(id);
    initialize(result);
    return result;
  }

  public class GetTaskEntity extends TaskEntityEndpointRequest<no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity> {

    private static final String REST_PATH = "taskentity/{id}";

    /**
     * Create a request for the method "getTaskEntity".
     *
     * This request holds the parameters needed by the the taskEntityEndpoint server.  After setting
     * any optional parameters, call the {@link GetTaskEntity#execute()} method to invoke the remote
     * operation. <p> {@link GetTaskEntity#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetTaskEntity(java.lang.String id) {
      super(TaskEntityEndpoint.this, "GET", REST_PATH, null, no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity.class);
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
    public GetTaskEntity setAlt(java.lang.String alt) {
      return (GetTaskEntity) super.setAlt(alt);
    }

    @Override
    public GetTaskEntity setFields(java.lang.String fields) {
      return (GetTaskEntity) super.setFields(fields);
    }

    @Override
    public GetTaskEntity setKey(java.lang.String key) {
      return (GetTaskEntity) super.setKey(key);
    }

    @Override
    public GetTaskEntity setOauthToken(java.lang.String oauthToken) {
      return (GetTaskEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public GetTaskEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetTaskEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetTaskEntity setQuotaUser(java.lang.String quotaUser) {
      return (GetTaskEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetTaskEntity setUserIp(java.lang.String userIp) {
      return (GetTaskEntity) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String id;

    /**

     */
    public java.lang.String getId() {
      return id;
    }

    public GetTaskEntity setId(java.lang.String id) {
      this.id = id;
      return this;
    }

    @Override
    public GetTaskEntity set(String parameterName, Object value) {
      return (GetTaskEntity) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertTaskEntity".
   *
   * This request holds the parameters needed by the taskEntityEndpoint server.  After setting any
   * optional parameters, call the {@link InsertTaskEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity}
   * @return the request
   */
  public InsertTaskEntity insertTaskEntity(no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity content) throws java.io.IOException {
    InsertTaskEntity result = new InsertTaskEntity(content);
    initialize(result);
    return result;
  }

  public class InsertTaskEntity extends TaskEntityEndpointRequest<no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity> {

    private static final String REST_PATH = "taskentity";

    /**
     * Create a request for the method "insertTaskEntity".
     *
     * This request holds the parameters needed by the the taskEntityEndpoint server.  After setting
     * any optional parameters, call the {@link InsertTaskEntity#execute()} method to invoke the
     * remote operation. <p> {@link InsertTaskEntity#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity}
     * @since 1.13
     */
    protected InsertTaskEntity(no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity content) {
      super(TaskEntityEndpoint.this, "POST", REST_PATH, content, no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity.class);
    }

    @Override
    public InsertTaskEntity setAlt(java.lang.String alt) {
      return (InsertTaskEntity) super.setAlt(alt);
    }

    @Override
    public InsertTaskEntity setFields(java.lang.String fields) {
      return (InsertTaskEntity) super.setFields(fields);
    }

    @Override
    public InsertTaskEntity setKey(java.lang.String key) {
      return (InsertTaskEntity) super.setKey(key);
    }

    @Override
    public InsertTaskEntity setOauthToken(java.lang.String oauthToken) {
      return (InsertTaskEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertTaskEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertTaskEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertTaskEntity setQuotaUser(java.lang.String quotaUser) {
      return (InsertTaskEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertTaskEntity setUserIp(java.lang.String userIp) {
      return (InsertTaskEntity) super.setUserIp(userIp);
    }

    @Override
    public InsertTaskEntity set(String parameterName, Object value) {
      return (InsertTaskEntity) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeTaskEntity".
   *
   * This request holds the parameters needed by the taskEntityEndpoint server.  After setting any
   * optional parameters, call the {@link RemoveTaskEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveTaskEntity removeTaskEntity(java.lang.String id) throws java.io.IOException {
    RemoveTaskEntity result = new RemoveTaskEntity(id);
    initialize(result);
    return result;
  }

  public class RemoveTaskEntity extends TaskEntityEndpointRequest<Void> {

    private static final String REST_PATH = "taskentity/{id}";

    /**
     * Create a request for the method "removeTaskEntity".
     *
     * This request holds the parameters needed by the the taskEntityEndpoint server.  After setting
     * any optional parameters, call the {@link RemoveTaskEntity#execute()} method to invoke the
     * remote operation. <p> {@link RemoveTaskEntity#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveTaskEntity(java.lang.String id) {
      super(TaskEntityEndpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveTaskEntity setAlt(java.lang.String alt) {
      return (RemoveTaskEntity) super.setAlt(alt);
    }

    @Override
    public RemoveTaskEntity setFields(java.lang.String fields) {
      return (RemoveTaskEntity) super.setFields(fields);
    }

    @Override
    public RemoveTaskEntity setKey(java.lang.String key) {
      return (RemoveTaskEntity) super.setKey(key);
    }

    @Override
    public RemoveTaskEntity setOauthToken(java.lang.String oauthToken) {
      return (RemoveTaskEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveTaskEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveTaskEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveTaskEntity setQuotaUser(java.lang.String quotaUser) {
      return (RemoveTaskEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveTaskEntity setUserIp(java.lang.String userIp) {
      return (RemoveTaskEntity) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String id;

    /**

     */
    public java.lang.String getId() {
      return id;
    }

    public RemoveTaskEntity setId(java.lang.String id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveTaskEntity set(String parameterName, Object value) {
      return (RemoveTaskEntity) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateTaskEntity".
   *
   * This request holds the parameters needed by the taskEntityEndpoint server.  After setting any
   * optional parameters, call the {@link UpdateTaskEntity#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity}
   * @return the request
   */
  public UpdateTaskEntity updateTaskEntity(no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity content) throws java.io.IOException {
    UpdateTaskEntity result = new UpdateTaskEntity(content);
    initialize(result);
    return result;
  }

  public class UpdateTaskEntity extends TaskEntityEndpointRequest<no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity> {

    private static final String REST_PATH = "taskentity";

    /**
     * Create a request for the method "updateTaskEntity".
     *
     * This request holds the parameters needed by the the taskEntityEndpoint server.  After setting
     * any optional parameters, call the {@link UpdateTaskEntity#execute()} method to invoke the
     * remote operation. <p> {@link UpdateTaskEntity#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity}
     * @since 1.13
     */
    protected UpdateTaskEntity(no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity content) {
      super(TaskEntityEndpoint.this, "PUT", REST_PATH, content, no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity.class);
    }

    @Override
    public UpdateTaskEntity setAlt(java.lang.String alt) {
      return (UpdateTaskEntity) super.setAlt(alt);
    }

    @Override
    public UpdateTaskEntity setFields(java.lang.String fields) {
      return (UpdateTaskEntity) super.setFields(fields);
    }

    @Override
    public UpdateTaskEntity setKey(java.lang.String key) {
      return (UpdateTaskEntity) super.setKey(key);
    }

    @Override
    public UpdateTaskEntity setOauthToken(java.lang.String oauthToken) {
      return (UpdateTaskEntity) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateTaskEntity setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateTaskEntity) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateTaskEntity setQuotaUser(java.lang.String quotaUser) {
      return (UpdateTaskEntity) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateTaskEntity setUserIp(java.lang.String userIp) {
      return (UpdateTaskEntity) super.setUserIp(userIp);
    }

    @Override
    public UpdateTaskEntity set(String parameterName, Object value) {
      return (UpdateTaskEntity) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link TaskEntityEndpoint}.
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

    /** Builds a new instance of {@link TaskEntityEndpoint}. */
    @Override
    public TaskEntityEndpoint build() {
      return new TaskEntityEndpoint(this);
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
     * Set the {@link TaskEntityEndpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setTaskEntityEndpointRequestInitializer(
        TaskEntityEndpointRequestInitializer taskentityendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(taskentityendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
