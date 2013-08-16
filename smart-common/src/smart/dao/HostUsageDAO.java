package smart.dao;

import java.util.List;

import smart.bean.CPUPerc;
import smart.bean.FileSystem;
import smart.bean.MemoryPerc;
import smart.bean.NetInterface;
import smart.bean.ProgressPerc;

public interface HostUsageDAO {

	public List<CPUPerc> queryCPUList(String cpuid);

	public List<FileSystem> queryFileSysList(String fileSysid);

	public List<MemoryPerc> queryMemoryList(String memid);

	public List<NetInterface> queryNIStatusList(String niId);

	public List<ProgressPerc> queryProList(String progressid);
}
