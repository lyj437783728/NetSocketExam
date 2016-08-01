先打开一个控制台，输入
mvn exec:java -Dexec.mainClass="com.hand.MyServerSocket" -Dexec.args="arg0 arg1 arg2"

然后再打开另一个控制台，输入
mvn exec:java -Dexec.mainClass="com.hand.ClientSocket" -Dexec.args="arg0 arg1 arg2"