﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Patient.Entity;

public class Room
{
    public int Id { get; set; }
    
    public List<Patient> Patients { get; set; }
}
