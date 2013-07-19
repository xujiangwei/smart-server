package smart.dao;

import java.util.List;

import smart.bean.CPU;
import smart.bean.FileSystem;
import smart.bean.Host;
import smart.bean.Memory;
import smart.bean.NetInterface;
import smart.bean.Progress;

public interface IHostDAO {

	/**
	 * 查询表中的所有host
	 * @return host列表
	 */
	public List<Host> queryAll();

	public CPU quersyCPU(String hostid);
	
	public Memory queryMemory(String hostid);
	
	public FileSystem queryFileSys(String hostid);
	
	public NetInterface queryNI(String hostid);
	
	public Progress queryPro(String hostid);
}
