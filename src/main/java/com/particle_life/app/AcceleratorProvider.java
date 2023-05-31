package com.particle_life.app;

import com.particle_life.Accelerator;
import com.particle_life.app.selection.InfoWrapper;
import com.particle_life.app.selection.InfoWrapperProvider;
import org.joml.Vector3f;

import java.util.List;

class AcceleratorProvider implements InfoWrapperProvider<Accelerator>{
  @Override
  public List<InfoWrapper<Accelerator>> create() {
    return List.of(
      new InfoWrapper<Accelerator>("particle life",(a,pos)-> {
        float rmin=0.3f;
        float dist=pos.length();
        float force=dist<rmin?(dist/rmin-1):a*(1-Math.abs(1+rmin-2*dist)/(1-rmin));
        return pos.mul(force/dist);
      }),
      new InfoWrapper<Accelerator>("particle life / r",(a,pos)-> {
        float rmin=0.3f;
        float dist=pos.length();
        float force=dist<rmin?(dist/rmin-1):a*(1-Math.abs(1+rmin-2*dist)/(1-rmin));
        return pos.mul(force/(dist*dist));
      }),
      new InfoWrapper<Accelerator>("particle life / r^2",(a,pos)-> {
        float rmin=0.3f;
        float dist=pos.length();
        float force=dist<rmin?(dist/rmin-1):a*(1-Math.abs(1+rmin-2*dist)/(1-rmin));
        return pos.mul(force/(dist*dist*dist));
      }),
      new InfoWrapper<Accelerator>("rotator 90deg",(a,pos)-> {
        float dist=pos.length();
        float force=a*(1-dist);
        Vector3f delta=new Vector3f(-pos.y,pos.x,0);
        return delta.mul(force/dist);
      }),
      new InfoWrapper<Accelerator>("rotator attr",(a,pos)-> {
        float dist=pos.length();
        float force=1-dist;
        float angle=(float)(-a*Math.PI);
        Vector3f delta=new Vector3f(
          (float)(Math.cos(angle)*pos.x+Math.sin(angle)*pos.y),
          (float)(-Math.sin(angle)*pos.x+Math.cos(angle)*pos.y),
          0);
        return delta.mul(force/dist);
      }),
      new InfoWrapper<Accelerator>("planets",
        "works best without friction (value = 1.0)",(a,pos)-> {
          Vector3f delta=new Vector3f(pos);
          float r=delta.length();
          r=Math.max(r,0.01f);
          return delta.mul(0.01f/(r*r*r));
        }));
  }
}
