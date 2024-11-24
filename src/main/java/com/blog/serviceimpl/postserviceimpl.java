package com.blog.serviceimpl;

import com.blog.entity.categories;
import com.blog.entity.posts;
import com.blog.entity.user;
import com.blog.exception.runexception;
import com.blog.payload.postdto;
import com.blog.payload.postresponse;
import com.blog.payload.userresponse;
import com.blog.repo.categoresrepo;
import com.blog.repo.postrepo;
import com.blog.repo.userrepo;
import com.blog.service.postservice;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class postserviceimpl implements postservice {

    private postrepo pore;

    private ModelMapper modelMapper;

    private userrepo us;

    private categoresrepo cr;

    @Override
    public postdto createpost(postdto p, Integer userId, Integer categeryId) {

        user u = this.us.findById(userId).orElseThrow(() -> new runexception("user", "Id", userId));
        categories ca = this.cr.findById(categeryId).orElseThrow(() -> new runexception("categery", "Id", categeryId));

        posts map = this.modelMapper.map(p, posts.class);
        map.setDate(new Date());
postdto pa =new postdto();
        map.setImage(pa.getImage());
        map.setU(u);
        map.setCa(ca);
        posts save = this.pore.save(map);

        return this.modelMapper.map(save,postdto.class);
    }

    @Override
    public postdto updatepost(postdto p, Integer postid) {
        posts po = this.pore.findById(postid).orElseThrow(() -> new runexception("id", "postid", postid));
po.setTitle(p.getTitle());
po.setContent(p.getContent());

po.setImage(p.getImage());
        posts save = this.pore.save(po);
        return this.modelMapper.map(save,postdto.class);
    }

    @Override
    public void delete(Integer postid) {
        posts p = this.pore.findById(postid).orElseThrow(() -> new runexception("postid", "post", postid));
        this.pore.delete(p);

    }

    @Override
    public postdto getpost(Integer postid) {
        posts p = this.pore.findById(postid).orElseThrow(() -> new runexception("postid", "posts", postid));

        return this.modelMapper.map(p,postdto.class);
    }

    @Override
    public postresponse getallpost(Integer pagenumber, Integer pagesize,String sortby) {

        Pageable p= PageRequest.of(pagenumber,pagesize, Sort.by(sortby));
        Page<posts> a = this.pore.findAll(p);
        List<posts> all = a.getContent();

        List<postdto> collect = all.stream().map((post) -> this.modelMapper.map(post, postdto.class)).collect(Collectors.toList());

        postresponse pos = new postresponse();
        pos.setContent(collect);
pos.setPagenumber(a.getNumber());
pos.setPagesize(a.getSize());
pos.setTotalpage(a.getTotalPages());
pos.setTotalement(a.getTotalElements());
pos.setLastpage(a.isLast());
        return pos;
    }

    @Override
    public List<postdto> getpostbycategories(Integer cid) {
        categories categories = this.cr.findById(cid).orElseThrow(() -> new runexception("categoriesid", "cid", cid));
        List<posts> byCa = this.pore.findByCa(categories);
        List<postdto> collect = byCa.stream().map((p) -> this.modelMapper.map(p, postdto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<postdto> getpostbyuser(Integer userId) {

        user u = this.us.findById(userId).orElseThrow(() -> new runexception("userid", "id", userId));
        List<posts> byU = this.pore.findByU(u);
        List<postdto> collect = byU.stream().map((useru) -> this.modelMapper.map(useru, postdto.class)).collect(Collectors.toList());
        return  collect;


    }

    @Override
    public List<postdto> searchpost(String keyboard) {
        List<posts> byT = this.pore.findByTitleContaining(keyboard);
        List<postdto> collect = byT.stream().map((post) -> this.modelMapper.map(post, postdto.class)).collect(Collectors.toList());
        return collect;
    }
}
