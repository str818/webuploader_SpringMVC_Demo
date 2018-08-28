<%@ page contentType="text/html; charset=utf-8" %> 
<html lang="zh-CN">
<head>
    
    <title>上传Logo</title>
    <link rel="stylesheet" type="text/css" href="css/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

	<script>
		var name = "name_test";
		var my_id = 11110000;
	</script>

		
    <div id="wrapper">
        <div id="container">
            <!--头部，相册选择和格式选择-->

            <div id="uploader">
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;">
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   	<script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/webuploader.js"></script>
    <script type="text/javascript" src="js/upload.js"></script>
</body>
</html>
