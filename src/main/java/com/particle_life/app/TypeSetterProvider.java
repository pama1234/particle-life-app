package com.particle_life.app;

import java.util.List;

import com.particle_life.DefaultTypeSetter;
import com.particle_life.TypeSetter;
import com.particle_life.app.selection.InfoWrapper;
import com.particle_life.app.selection.InfoWrapperProvider;

import pama1234.math.UtilMath;

public class TypeSetterProvider implements InfoWrapperProvider<TypeSetter>{
  @Override
  public List<InfoWrapper<TypeSetter>> create() {
    return List.of(
      new InfoWrapper<TypeSetter>("fully random",new DefaultTypeSetter()),
      new InfoWrapper<TypeSetter>("randomize 10%",
        (position,velocity,type,nTypes)->UtilMath.random()<0.1?mapType(UtilMath.random(),nTypes):type),
      new InfoWrapper<TypeSetter>("slices",
        (position,velocity,type,nTypes)->mapType(0.5f*position.x+0.5f,nTypes)),
      new InfoWrapper<TypeSetter>("onion",
        (position,velocity,type,nTypes)->mapType(position.length(),nTypes)),
      new InfoWrapper<TypeSetter>("rotate",
        (position,velocity,type,nTypes)->(type+1)%nTypes),
      new InfoWrapper<TypeSetter>("flip",
        (position,velocity,type,nTypes)->nTypes-1-type),
      new InfoWrapper<TypeSetter>("more of first",
        (position,velocity,type,nTypes)->mapType(UtilMath.random()*UtilMath.random(),nTypes)),
      new InfoWrapper<TypeSetter>("kill still",
        (position,velocity,type,nTypes)->velocity.length()<0.01?nTypes-1:type));
  }
  private static int constrain(int value,int nTypes) {
    return Math.max(0,Math.min(nTypes-1,value));
  }
  private static int mapType(float value,int nTypes) {
    return constrain((int)Math.floor(value*nTypes),nTypes);
  }
}
