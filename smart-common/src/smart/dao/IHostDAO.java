package smart.dao;

import java.util.List;

import smart.dto.CPU;
import smart.dto.FileSystem;
import smart.dto.Host;
import smart.dto.Memory;
import smart.dto.NetInterface;
import smart.dto.Ping;
import smart.dto.Progress;

public interface IHostDAO {

	/**
	 * 查询表中的所有host
	 * @return host列表
	 */
	public List<Host> queryAll();

	public CPU queryCPU(String hostid);
	
	public List<Ping> queryPing(String hostid);
	
	public Memory queryMemory(String hostid);
	
	public List<FileSystem> queryFileSys(String hostid);
	
	public List<NetInterface> queryNI(String hostid);
	
	public Progress queryPro(String hostid);
}
