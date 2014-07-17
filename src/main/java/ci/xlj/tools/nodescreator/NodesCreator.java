package ci.xlj.tools.nodescreator;

import ci.xlj.libs.jenkinsvisitor.JenkinsVisitor;
import ci.xlj.libs.utils.StringUtils;

public class NodesCreator {

	public static void main(String[] args) {

		if (!StringUtils.isValid(args)) {
			System.out.println("Invalid parameters. Please check and retry.");
			System.exit(-1);
		}

		JenkinsVisitor v = new JenkinsVisitor(args[0]);
		boolean isLogin = v.login(args[1], args[2]);

		if (isLogin) {
			StringBuilder b = new StringBuilder();
			b.append(args[3]).append(",").append(args[4]).append(",")
					.append(args[5]).append(",").append(args[6]).append(",")
					.append(args[7]);
			v.doPost(args[0] + "/plugin/pi-nodescreator/createNode",
					b.toString());

			if (v.getResponseStatusCode() == 200) {
				System.out.println("Node added.");
			} else {
				System.err.println("Error occured. Reasons:\n"
						+ v.getResponseContent());
			}

		}
	}
}