package com.particle_life.app;

import java.util.List;
import java.util.Random;

import com.particle_life.DefaultPositionSetter;
import com.particle_life.PositionSetter;
import com.particle_life.app.selection.InfoWrapper;
import com.particle_life.app.selection.InfoWrapperProvider;

import pama1234.math.UtilMath;

class PositionSetterProvider implements InfoWrapperProvider<PositionSetter>{
  private static final Random random=new Random();
  @Override
  public List<InfoWrapper<PositionSetter>> create() {
    return List.of(
      new InfoWrapper<PositionSetter>("uniform",new DefaultPositionSetter()),
      new InfoWrapper<PositionSetter>("uniform circle",(position,type,nTypes)-> {
        float angle=UtilMath.random()*2*UtilMath.PI;
        float radius=UtilMath.sqrt(UtilMath.random());
        position.x=UtilMath.cos(angle)*radius;
        position.y=UtilMath.sin(angle)*radius;
      }),
      new InfoWrapper<PositionSetter>("centered",(position,type,nTypes)-> {
        position.x=(float)(random.nextGaussian()*0.3f);
        position.y=(float)(random.nextGaussian()*0.3f);
      }),
      new InfoWrapper<PositionSetter>("centered circle",(position,type,nTypes)-> {
        float angle=UtilMath.random()*2*UtilMath.PI;
        float radius=UtilMath.random();
        position.x=UtilMath.cos(angle)*radius;
        position.y=UtilMath.sin(angle)*radius;
      }),
      new InfoWrapper<PositionSetter>("ring",(position,type,nTypes)-> {
        float angle=UtilMath.random()*2*UtilMath.PI;
        float radius=0.9f+0.02f*(float)random.nextGaussian();
        position.x=UtilMath.cos(angle)*radius;
        position.y=UtilMath.sin(angle)*radius;
      }),
      new InfoWrapper<PositionSetter>("type battle",(position,type,nTypes)-> {
        float centerAngle=type/(float)nTypes*2*UtilMath.PI;
        float centerRadius=0.5f;
        float angle=UtilMath.random()*2*UtilMath.PI;
        float radius=UtilMath.random()*0.1f;
        position.x=centerRadius*UtilMath.cos(centerAngle)+UtilMath.cos(angle)*radius;
        position.y=centerRadius*UtilMath.sin(centerAngle)+UtilMath.sin(angle)*radius;
      }),
      new InfoWrapper<PositionSetter>("type wheel",(position,type,nTypes)-> {
        float centerAngle=type/(float)nTypes*2*UtilMath.PI;
        float centerRadius=0.3f;
        float individualRadius=0.2f;
        position.x=centerRadius*UtilMath.cos(centerAngle)+(float)random.nextGaussian()*individualRadius;
        position.y=centerRadius*UtilMath.sin(centerAngle)+(float)random.nextGaussian()*individualRadius;
      }),
      new InfoWrapper<PositionSetter>("line",(position,type,nTypes)-> {
        position.x=(2*random.nextFloat()-1);
        position.y=(2*random.nextFloat()-1)*0.15f;
      }),
      new InfoWrapper<PositionSetter>("spiral",(position,type,nTypes)-> {
        float maxRotations=2;
        float f=random.nextFloat();
        float angle=maxRotations*2*UtilMath.PI*f;
        float spread=0.5f*UtilMath.min(f,0.2f);
        float radius=0.9f*f+spread*(float)random.nextGaussian()*spread;
        position.x=radius*UtilMath.cos(angle);
        position.y=radius*UtilMath.sin(angle);
      }),
      new InfoWrapper<PositionSetter>("rainbow spiral",(position,type,nTypes)-> {
        float maxRotations=2;
        float typeSpread=0.3f/nTypes;
        float f=(type+1)/(float)(nTypes+2)+typeSpread*(float)random.nextGaussian();
        if(f<0) {
          f=0;
        }else if(f>1) {
          f=1;
        }
        float angle=maxRotations*2*UtilMath.PI*f;
        float spread=0.5f*UtilMath.min(f,0.2f);
        float radius=0.9f*f+spread*(float)random.nextGaussian()*spread;
        position.x=radius*UtilMath.cos(angle);
        position.y=radius*UtilMath.sin(angle);
      }));
  }
}
