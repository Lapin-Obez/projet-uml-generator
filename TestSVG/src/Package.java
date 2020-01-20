package src;

import java.util.ArrayList;
import java.util.List;

public class Package {
		private String name;
		private List<Classe> classe;
		private int x =0;
		private int y = 0;
		private int larg = 0;
		private int longu = 0;
		
		public Package(String name) {
			super();
			this.name = name;
			this.classe = new ArrayList<>();
		}

		public int getX() {
			return x;
		}

		public List<Classe> getClasse() {
			return classe;
		}

		public void addClasse(Classe c) {
			this.classe.add(c);
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getLarg() {
			return larg;
		}

		public void setLarg(int larg) {
			this.larg = larg;
		}

		public int getLongu() {
			return longu;
		}

		public void setLongu(int longu) {
			this.longu = longu;
		}

		public String getName() {
			return name;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Package other = (Package) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
		
		
		
}
