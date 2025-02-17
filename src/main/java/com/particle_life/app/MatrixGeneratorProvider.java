package com.particle_life.app;

import com.particle_life.DefaultMatrix;
import com.particle_life.DefaultMatrixGenerator;
import com.particle_life.MatrixGenerator;
import com.particle_life.app.selection.InfoWrapper;
import com.particle_life.app.selection.InfoWrapperProvider;

import java.util.List;

class MatrixGeneratorProvider implements InfoWrapperProvider<MatrixGenerator>{
  @Override
  public List<InfoWrapper<MatrixGenerator>> create() {
    return List.of(
      new InfoWrapper<MatrixGenerator>("fully random",new DefaultMatrixGenerator()),
      new InfoWrapper<MatrixGenerator>("symmetry",size-> {
        DefaultMatrix m=new DefaultMatrix(size);
        m.randomize();
        for(int i=0;i<m.size();i++) {
          for(int j=i;j<m.size();j++) {
            m.set(i,j,m.get(j,i));
          }
        }
        return m;
      }),
      new InfoWrapper<MatrixGenerator>("chains",size-> {
        DefaultMatrix m=new DefaultMatrix(size);
        for(int i=0;i<size;i++) {
          for(int j=0;j<size;j++) {
            if(j==i||j==(i+1)%size||j==(i+size-1)%size) {
              m.set(i,j,1);
            }else {
              m.set(i,j,-1);
            }
          }
        }
        return m;
      }),
      new InfoWrapper<MatrixGenerator>("chains 2",size-> {
        DefaultMatrix m=new DefaultMatrix(size);
        for(int i=0;i<size;i++) {
          for(int j=0;j<size;j++) {
            if(j==i) {
              m.set(i,j,1);
            }else if(j==(i+1)%size||j==(i+size-1)%size) {
              m.set(i,j,0.2f);
            }else {
              m.set(i,j,-1);
            }
          }
        }
        return m;
      }),
      new InfoWrapper<MatrixGenerator>("chains 3",size-> {
        DefaultMatrix m=new DefaultMatrix(size);
        for(int i=0;i<size;i++) {
          for(int j=0;j<size;j++) {
            if(j==i) {
              m.set(i,j,1);
            }else if(j==(i+1)%size||j==(i+size-1)%size) {
              m.set(i,j,0.2f);
            }else {
              m.set(i,j,0);
            }
          }
        }
        return m;
      }),
      new InfoWrapper<MatrixGenerator>("snakes",size-> {
        DefaultMatrix m=new DefaultMatrix(size);
        for(int i=0;i<size;i++) {
          m.set(i,i,1);
          m.set(i,(i+1)%m.size(),0.2f);
        }
        return m;
      }),
      new InfoWrapper<MatrixGenerator>("zero",DefaultMatrix::new));
  }
}
