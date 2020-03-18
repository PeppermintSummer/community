package life.majiang.community.provider;

import com.aliyun.oss.OSSClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class AliCloudOSSProvider {
    private static String ENDPOINT="oss-cn-beijing.aliyuncs.com";
    private static String ACCESSKEYID="sdf";
    private static String ACCESSKEYSECRET="sdf";
    private static String BUCKETNAME="";
    private static String SUFFER_URL="http://.oss-cn-beijing.aliyuncs.com/"; //上传返回成功后的url
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //格式化日期

    /**
     * 获取OSS连接
     */
    public OSSClient getOSSClient(){
        // 创建OSSClient实例。
        //OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建存储空间。
        //ossClient.createBucket(bucketName);
        // 关闭OSSClient。
        //ossClient.shutdown();

        //创建一个OSSClient对象
        OSSClient ossClient = new OSSClient(ENDPOINT,ACCESSKEYID,ACCESSKEYSECRET);

        if (ossClient.doesBucketExist(BUCKETNAME)){
            System.out.println("bucket成功创建");
            ossClient.shutdown();

        }else {
            System.out.println("创建失败");
            //CreateBucketRequest bucketRequest=
            ossClient.shutdown();
        }
        return ossClient;
    }

    /**
     * 上传文件
     * @param multipartFile 需要上传的文件
     * @Param businessType 上传文件的类型
     * @return
     */
    public String upoloadDocument(MultipartFile multipartFile,String businessType){
        //获取连接
        OSSClient ossClient=this.getOSSClient();
        //获取文件的后缀名称
        String ext=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        //上传至oss的哪个文件夹,通过filename来指定 //www.baidu.com/img/273712/asd.jpg
        String fileName=getFileName(businessType, ext);

        String url=null;
        //通过ossCilent来获取上传文件后返回的url
        try {
            ossClient.putObject(BUCKETNAME,fileName,new ByteArrayInputStream(multipartFile.getBytes()));

            url= SUFFER_URL+fileName;
            System.out.println("上传成功,oss地址："+url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ossClient.shutdown();
        }
        return url;
    }



    private String getFileName(String businessType, String ext) {
        String date= sdf.format(new Date());
        if (StringUtils.isEmpty(businessType)){
            businessType="default";
        }
        //为了避免图片重名，使用UUID来命名图片
        String uuid= UUID.randomUUID().toString().replace("-","");
        //组合filename
        String fileName=businessType+"/"+date+"/"+uuid+ext;
        return fileName;
    }

}
