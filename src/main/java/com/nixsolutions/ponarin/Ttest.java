package com.nixsolutions.ponarin;

import com.nixsolutions.ponarin.dao.RoleDao;
import com.nixsolutions.ponarin.dao.impl.HibernateRoleDao;
import com.nixsolutions.ponarin.entity.Role;
import com.nixsolutions.ponarin.utils.HibernateUtil;

public class Ttest {
    public static void main(String[] args) {
        try {
            RoleDao roleDao = new HibernateRoleDao();
            Role roleA = new Role();
            roleA.setName("Admin");
            
            roleDao.create(roleA);
            
            Role role = roleDao.findByName(roleA.getName());
            System.out.println(role);
            roleDao.remove(role);
        } catch (Exception ex) {
            ex.printStackTrace();
            
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
        
        
        
    }
}
