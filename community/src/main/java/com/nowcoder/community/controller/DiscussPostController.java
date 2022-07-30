package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title,String content){
        User user = hostHolder.getUser();
        if(user == null){
            return CommunityUtil.getJSONString(403, "你还没有登录哦!");
        }
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);
        // 报错的情况,将来统一处理.
        return CommunityUtil.getJSONString(0,"发布成功!");

    }

    //在DisscussPostController下添加方法getDiscussPost
    //1.声明访问路径 path = "/detail/{discussPostId}，请求方式 method = RequestMethod.GET
    //2.需要返回模板，不要写responsebody
    //3.方法中加参数接受变量，需要用到路径中的 “discussPostId”，查询的结果通过model携带数据
    //4.调用discussPostService.findDiscussPostById得到 DiscussPost 的id
    //5.用户id要处理。法一在mapper中使用关联查询，法二先查帖子数据，得到id再查用户名。前者效率肯定高，但是查询方法有些冗余耦合，不是所有业务需要。法二简单方便，后面通过redis提高效率。


    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model){
        //帖子
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post",post);
        //作者
        User user = userService.findUserById(post.getUserId());
        model.addAttribute("user",user);

        return "/site/discuss-detail";

    }
}
