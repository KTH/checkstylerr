package edu.internet2.middleware.grouper.app.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import edu.internet2.middleware.grouper.cfg.dbConfig.ConfigItemFormElement;
import edu.internet2.middleware.grouper.cfg.dbConfig.ConfigItemMetadata;
import edu.internet2.middleware.grouper.cfg.dbConfig.ConfigItemMetadataType;
import edu.internet2.middleware.grouper.cfg.text.GrouperTextContainer;
import edu.internet2.middleware.grouper.util.GrouperUtil;
import edu.internet2.middleware.grouper.util.GrouperUtilElSafe;
import edu.internet2.middleware.grouperClient.collections.MultiKey;

public class GrouperConfigurationModuleAttribute {
  
  private GrouperConfigurationModuleBase grouperConfigModule;
  
  /**
   * config item metadata type (eg: Boolean, String, etc)
   */
  private  ConfigItemMetadataType type;
  
  /**
   * is this attribute required or not
   */
  private boolean required;
  
  /**
   * is this attribute read only or not
   */
  private boolean readOnly;
  
  /**
   * value for the attribute
   */
  private String value;
  
  /**
   * config suffix 
   */
  private String configSuffix;
  
  /**
   * default value for the attribute
   */
  private String defaultValue;
  
  /**
   * type of html element to render (Textfield, Textarea, etc)
   */
  private ConfigItemFormElement formElement;
  
  /**
   * full property name
   */
  private String fullPropertyName;
  
  /**
   * does this attribute store expression language 
   */
  private boolean expressionLanguage;
  
  /**
   * does this attribute store password
   */
  private boolean password;
  
  /**
   * value when this attribute stores expression language
   */
  private String expressionLanguageScript;
  
  /**
   * first one is the value and the second one is the label  
   */
  private List<MultiKey> dropdownValuesAndLabels;

  
  
  public GrouperConfigurationModuleBase getGrouperConfigModule() {
    return grouperConfigModule;
  }

  public void setGrouperConfigModule(GrouperConfigurationModuleBase grouperConfigModule) {
    this.grouperConfigModule = grouperConfigModule;
  }


  public ConfigItemMetadataType getType() {
    return type;
  }

  
  public void setType(ConfigItemMetadataType type) {
    this.type = type;
  }

  
  public boolean isRequired() {
    return required;
  }

  
  public void setRequired(boolean required) {
    this.required = required;
  }

  
  public boolean isReadOnly() {
    return readOnly;
  }

  
  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }

  public String getValue() {
    return value;
  }

  
  public void setValue(String value) {
    this.value = value;
  }

  
  public String getConfigSuffix() {
    return configSuffix;
  }

  
  public void setConfigSuffix(String configSuffix) {
    this.configSuffix = configSuffix;
  }

  
  public String getDefaultValue() {
    return defaultValue;
  }

  
  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  
  public ConfigItemFormElement getFormElement() {
    return formElement;
  }

  
  public void setFormElement(ConfigItemFormElement formElement) {
    this.formElement = formElement;
  }

  
  public String getFullPropertyName() {
    return fullPropertyName;
  }

  
  public void setFullPropertyName(String fullPropertyName) {
    this.fullPropertyName = fullPropertyName;
  }
  
  
  /**
   * config item metadata this attribute is backed with
   */
  private ConfigItemMetadata configItemMetadata;
  
  /**
   * config item metadata this attribute is backed with
   * @return
   */
  public ConfigItemMetadata getConfigItemMetadata() {
    return configItemMetadata;
  }
  
  /**
   * config item metadata this attribute is backed with
   * @param configItemMetadata
   */
  public void setConfigItemMetadata(ConfigItemMetadata configItemMetadata) {
    this.configItemMetadata = configItemMetadata;
  }
  
  /**
   * get label to display for this attribute
   * @return
   */
  public String getLabel() {
    String label = GrouperTextContainer.textOrNull("config." + this.getGrouperConfigModule().getClass().getSimpleName() + ".attribute." + this.getConfigSuffix() + ".label");
    if (StringUtils.isBlank(label)) {
      return this.getConfigSuffix();
    }
    return label;
  }

  /**
   * get description to display for this attribute
   * @return
   */
  public String getDescription() {
    //TODO rename daemonConfig to something more generic or get it from the implementation class
    String description = GrouperTextContainer.textOrNull("config." + this.getGrouperConfigModule().getClass().getSimpleName() + ".attribute." + this.getConfigSuffix() + ".description");
    if (StringUtils.isBlank(description)) {
      return this.getConfigItemMetadata().getComment();
    }
    return description;
  }


  
  public boolean isExpressionLanguage() {
    return expressionLanguage;
  }


  
  public void setExpressionLanguage(boolean expressionLanguage) {
    this.expressionLanguage = expressionLanguage;
  }


  
  public boolean isPassword() {
    return password;
  }


  
  public void setPassword(boolean password) {
    this.password = password;
  }


  
  public String getExpressionLanguageScript() {
    return expressionLanguageScript;
  }


  
  public void setExpressionLanguageScript(String expressionLanguageScript) {
    this.expressionLanguageScript = expressionLanguageScript;
  }


  
  public List<MultiKey> getDropdownValuesAndLabels() {
    return dropdownValuesAndLabels;
  }


  
  public void setDropdownValuesAndLabels(List<MultiKey> dropdownValuesAndLabels) {
    this.dropdownValuesAndLabels = dropdownValuesAndLabels;
  }
  
  /**
   * get the value or the expression language evaluation
   * @return the value
   */
  public String getValueOrExpressionEvaluation() {
    String value = null;
    
    if (this.isExpressionLanguage()) {
      value = this.getExpressionLanguageScript() != null? this.getExpressionLanguageScript(): null;
    } else if (this.getValue() != null) {
      value = this.getValue();
    }
    return value;
  }
  
  public boolean isHasValue() {
    return (!this.isExpressionLanguage() && !StringUtils.isBlank(this.getValue()))
        || (this.isExpressionLanguage() && !StringUtils.isBlank(this.getExpressionLanguageScript()));
  }
  
  /**
   * get the html id for the field 
   * @return
   */
  public String getHtmlForElementIdHandle() {
    return "#config_" + this.getConfigSuffix() + "_id";
  }
  
  /**
   * @return get evaluated value for validation
   */
  public String getEvaluatedValueForValidation() throws UnsupportedOperationException {
    
    if (!this.expressionLanguage) {
      return this.value;
    }
    
    // if script and certain type (e.g. env var), then validate
    //${java.lang.System.getenv().get('JAVA_HOME')}
    Pattern pattern = Pattern.compile("^\\$\\{java\\.lang\\.System\\.getenv\\(\\)\\.get\\('([\\w]+)'\\)\\}");
    boolean evaluate = false;
    if (pattern.matcher(this.expressionLanguageScript).matches()) {
      evaluate = true;
    }
    
    if (evaluate) {
      Map<String, Object> variableMap = new HashMap<String, Object>();

      variableMap.put("grouperUtil", new GrouperUtilElSafe());
      
      String value = GrouperUtil.substituteExpressionLanguage(this.expressionLanguageScript, variableMap, true, true, true);

      return value;
    }
    
    throw new UnsupportedOperationException();
  }
  
  /**
   * @return converted value to correct configItemMetadataType from string
   */
  public Object getObjectValueAllowInvalid() {
    
    ConfigItemMetadataType valueType =  GrouperUtil.defaultIfNull(this.getConfigItemMetadata().getValueType(), ConfigItemMetadataType.STRING);
    
    return valueType.convertValue(this.getValueOrExpressionEvaluation(), false);
    
  }
  
  /**
   * 
   * @return if show
   */
  public boolean isShow() {
    
    {
      Boolean showOverride = this.grouperConfigModule.showAttributeOverride(this.configSuffix);
      if (showOverride != null) {
        return showOverride;
      }
    }
    
    String showEl = this.getConfigItemMetadata().getShowEl();
    if (StringUtils.isBlank(showEl)) {
      return true;
    }
    
    Map<String, Object> variableMap = new HashMap<String, Object>();

    variableMap.put("grouperUtil", new GrouperUtilElSafe());
    
    for (GrouperConfigurationModuleAttribute grouperConfigModuleAttribute : this.grouperConfigModule.retrieveAttributes().values()) {
      
      variableMap.put(grouperConfigModuleAttribute.getConfigSuffix(), grouperConfigModuleAttribute.getObjectValueAllowInvalid());
      
    }

    String showString = GrouperUtil.substituteExpressionLanguage(showEl, variableMap, true, true, true);
    
    return GrouperUtil.booleanValue(showString, true);
  }

}
