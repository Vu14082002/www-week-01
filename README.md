<!-- prettier-ignore-start -->

# WWW Tuần 1

### Hướng dẫn sử dụng

#### 1 Đăng nhập

> User name: Nhập email hoặc account id
> Password : Nhập password <br>
>![NOTE](https://img.shields.io/badge/NOTE:-%23dc3545.svg) **Web có Authentication  và Authorization**
---

#### 2 Chức năng

##### 2.1. Role user <br>

> Page mà user role có thể truy cập vào sau khi đăng nhập thành công <br>
> **`controllerservlet?action=homepage`** <br>
> *Hiển thi thông tin mà user vừa đăng nhập* <br>

##### 2.2 Role admin <br>
> Page mà chỉ có account Role Admin có thể sử dụng sau khi đăng nhập thành công <br>

**2.2.1 ACCOUHT MANAGER**

>![CREATE](https://img.shields.io/badge/CREATE:-%2300CC66.svg) <br>
>**`/account?action=add`** <br>
> Thêm 1 account <br>

>![READ](https://img.shields.io/badge/READ:-%230066CC.svg) <br>
>**`/account?action=accounts`** <br>
> Hiển thị danh sách account <br>

> ![UPDATE:](https://img.shields.io/badge/UPDATE:-%23FF9900.svg) <br>
> **`/account?action=update&id=teo`** <br>
> Update account có account id = teo <br>

> ![DELETE](https://img.shields.io/badge/DELETE:-%23FF3333.svg) <br>
>Cập nhật Trạng thái của account thành **DELETE** <br>
***

**2.2.2 ROLE MANAGER**

>![CREATE](https://img.shields.io/badge/CREATE:-%2300CC66.svg) <br>
>**`/role?action=add`** <br>
> Thêm 1 role <br>

>![READ](https://img.shields.io/badge/READ:-%230066CC.svg) <br>
>**`/role?action=roles`** <br>
> Hiển thị danh sách role <br>

> ![UPDATE:](https://img.shields.io/badge/UPDATE:-%23FF9900.svg) <br>
> **`/role?action=update&id=user`** <br>
> Update role có id = user <br>

> ![DELETE](https://img.shields.io/badge/DELETE:-%23FF3333.svg) <br>
>Cập nhật Trạng thái của role thành **DELETE** <br>
***


**2.2.3 LOG MANAGER**  <br>

>![READ](https://img.shields.io/badge/READ:-%230066CC.svg)<br>
>**`/log?action=logs`** <br>
> Hiển thị danh sách log<br>

> ![UPDATE:](https://img.shields.io/badge/UPDATE:-%23FF9900.svg)<br>
> **`log?action=update&id=1`** <br>
> Update Note có log id = 1 <br>

***


**2.2.4 GRANT-ACCESS MANAGER**

>![CREATE](https://img.shields.io/badge/CREATE:-%2300CC66.svg)<br>
>**`/grant-access?action=add`** <br>
> Thêm 1 quyền try cập mới cho 1 account <br>

>![READ](https://img.shields.io/badge/READ:-%230066CC.svg)<br>
>**`/grant-access?action=grant-accesses`**<br>
> Hiển thị danh sách phân quyên truy cập của mỗi account<br>

> ![UPDATE:](https://img.shields.io/badge/UPDATE:-%23FF9900.svg)<br>
> **`/grant-access?action=update&account=met&role=user`**<br>
> Update grant role =user của account có id = met<br>

> ![DELETE](https://img.shields.io/badge/DELETE:-%23FF3333.svg)<br>
>Cập nhật quyên truy cập của account chọn thành **DIASABLE** <br>
***
##### 2.3. LOGOUT <br>
> Chuyển về trang đăng nhập và xóa session


***


<!-- prettier-ignore-end -->
