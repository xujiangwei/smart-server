package smart.dao;

import java.util.List;

import smart.dto.CPUsage;
import smart.dto.FileSysUsage;
import smart.dto.MemUsage;
import smart.dto.NetInterfaceStatus;
import smart.dto.ProgressUsage;

public interface IHostUsageDAO {

	public List<CPUsage> queryCPUList(String cpuid);
	
	public List<FileSysUsage> queryFileSysList(String fileSysid);
	
	public List<MemUsage> queryMemoryList(String memid);
	
	public List<NetInterfaceStatus> queryNIStatusList(String niId);
	
	public List<ProgressUsage> queryProList(String progressid);
}
