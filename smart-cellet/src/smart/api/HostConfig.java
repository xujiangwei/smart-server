package smart.api;/** * @ClassName   : HostConfig * @Description :将不同系统的IP，端口、工程名称单独进行配置管理，由各子系统开发人员单独管理与维护。* 				对于同一个集成项目，端口是不同的；对于不同的项目，或者非集中式部署项目，IP，端口，工程名称每次都会发生变化。* @author      : dwg* @date        : Sep 22, 2013 10:05:58 AM *  */public interface HostConfig {		/**	* @Description: 获取主机名称，包括IP和端口完整的配置。示例：http://127.0.0.1:8080	* @param      : @return    	* @return     : String   	* @throws	 */	public String getHost();		/**	* @Description: 获取项目工程名称，示例: itims、itsm	* @param      : @return    	* @return     : String   	* @throws	 */	public String getProjectName();	/**	* @Description: 获取完整的URL地址。示例：http://localhost:8080/itsm	* @param      : @return    	* @return     : String   	* @throws	 */	public String getHostInfo() ;}