package c06realm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Role implements Serializable {
	private Long id;
	private String role; //角色标识，程序中判断使用，如"admin"
	private String description; //角色描述，UI界面显示使用
	private Boolean available = Boolean.FALSE; //是否可用，如果不可用将不会添加给用户

	public Role(String role, String description, Boolean available) {
		this.role = role;
		this.description = description;
		this.available = available;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (id != null ? !id.equals(role.id) : role.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
