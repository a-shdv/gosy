using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Patient.Entity;

namespace Patient.Repo;

public class DbCtx : DbContext
{
    private const string hostname = "DESKTOP-D8ML0DU";

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        if (optionsBuilder.IsConfigured == false)
        {
            optionsBuilder.UseSqlServer(@"Data Source=" + hostname + ";Initial Catalog=Database;Integrated Security=True;MultipleActiveResultSets=True;TrustServerCertificate=True;");
        }
        base.OnConfiguring(optionsBuilder);
    }

    public virtual DbSet<Patient.Entity.Patient> Patients { set; get; }
    public virtual DbSet<Patient.Entity.Room> Rooms { set; get; }

}
