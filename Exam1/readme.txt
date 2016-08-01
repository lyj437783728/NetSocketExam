mvn exec:java -Dexec.mainClass="com.hand.GetPDF" -Dexec.args="arg0 arg1 arg2"

有时若因为网络波动或其他不知原因，第一次失败或下载的文件不能打开，
那么重新执行一遍上面的语句，就能解决。
也有时候文件打开会是乱码，原因未知，重新执行上面的语句，直到正常为止