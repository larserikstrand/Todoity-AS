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

package no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model;

/**
 * Model definition for TaskEntity.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the taskEntityEndpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class TaskEntity extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean active;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String address;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String category;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String date;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String description;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean finished;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String fixedEnd;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String fixedStart;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double latitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double longitude;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer priority;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String timeEnded;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long timeSpent;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String timeStarted;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getActive() {
    return active;
  }

  /**
   * @param active active or {@code null} for none
   */
  public TaskEntity setActive(java.lang.Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAddress() {
    return address;
  }

  /**
   * @param address address or {@code null} for none
   */
  public TaskEntity setAddress(java.lang.String address) {
    this.address = address;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCategory() {
    return category;
  }

  /**
   * @param category category or {@code null} for none
   */
  public TaskEntity setCategory(java.lang.String category) {
    this.category = category;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDate() {
    return date;
  }

  /**
   * @param date date or {@code null} for none
   */
  public TaskEntity setDate(java.lang.String date) {
    this.date = date;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * @param description description or {@code null} for none
   */
  public TaskEntity setDescription(java.lang.String description) {
    this.description = description;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getFinished() {
    return finished;
  }

  /**
   * @param finished finished or {@code null} for none
   */
  public TaskEntity setFinished(java.lang.Boolean finished) {
    this.finished = finished;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFixedEnd() {
    return fixedEnd;
  }

  /**
   * @param fixedEnd fixedEnd or {@code null} for none
   */
  public TaskEntity setFixedEnd(java.lang.String fixedEnd) {
    this.fixedEnd = fixedEnd;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getFixedStart() {
    return fixedStart;
  }

  /**
   * @param fixedStart fixedStart or {@code null} for none
   */
  public TaskEntity setFixedStart(java.lang.String fixedStart) {
    this.fixedStart = fixedStart;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public TaskEntity setId(java.lang.String id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLatitude() {
    return latitude;
  }

  /**
   * @param latitude latitude or {@code null} for none
   */
  public TaskEntity setLatitude(java.lang.Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getLongitude() {
    return longitude;
  }

  /**
   * @param longitude longitude or {@code null} for none
   */
  public TaskEntity setLongitude(java.lang.Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getPriority() {
    return priority;
  }

  /**
   * @param priority priority or {@code null} for none
   */
  public TaskEntity setPriority(java.lang.Integer priority) {
    this.priority = priority;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTimeEnded() {
    return timeEnded;
  }

  /**
   * @param timeEnded timeEnded or {@code null} for none
   */
  public TaskEntity setTimeEnded(java.lang.String timeEnded) {
    this.timeEnded = timeEnded;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getTimeSpent() {
    return timeSpent;
  }

  /**
   * @param timeSpent timeSpent or {@code null} for none
   */
  public TaskEntity setTimeSpent(java.lang.Long timeSpent) {
    this.timeSpent = timeSpent;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTimeStarted() {
    return timeStarted;
  }

  /**
   * @param timeStarted timeStarted or {@code null} for none
   */
  public TaskEntity setTimeStarted(java.lang.String timeStarted) {
    this.timeStarted = timeStarted;
    return this;
  }

  @Override
  public TaskEntity set(String fieldName, Object value) {
    return (TaskEntity) super.set(fieldName, value);
  }

  @Override
  public TaskEntity clone() {
    return (TaskEntity) super.clone();
  }

}
